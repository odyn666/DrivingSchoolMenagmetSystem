"use strict";
var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
Object.defineProperty(exports, "__esModule", { value: true });
// @ts-nocheck
var debounce_js_1 = require("@polymer/polymer/lib/utils/debounce.js");
var async_js_1 = require("@polymer/polymer/lib/utils/async.js");
var vaadin_grid_js_1 = require("@vaadin/grid/src/vaadin-grid.js");
var vaadin_grid_active_item_mixin_js_1 = require("@vaadin/grid/src/vaadin-grid-active-item-mixin.js");
var vaadin_grid_flow_selection_column_js_1 = require("./vaadin-grid-flow-selection-column.js");
(function () {
    var tryCatchWrapper = function (callback) {
        return window.Vaadin.Flow.tryCatchWrapper(callback, 'Vaadin Grid');
    };
    window.Vaadin.Flow.gridConnector = {
        initLazy: function (grid) {
            return tryCatchWrapper(function (grid) {
                // Check whether the connector was already initialized for the grid
                if (grid.$connector) {
                    return;
                }
                var dataProviderController = grid._dataProviderController;
                dataProviderController.ensureFlatIndexHierarchyOriginal = dataProviderController.ensureFlatIndexHierarchy;
                dataProviderController.ensureFlatIndexHierarchy = tryCatchWrapper(function (flatIndex) {
                    var item = this.getFlatIndexContext(flatIndex).item;
                    if (!item || !this.isExpanded(item)) {
                        return;
                    }
                    var isCached = grid.$connector.hasCacheForParentKey(grid.getItemId(item));
                    if (isCached) {
                        // The sub-cache items are already in the connector's cache. Skip the debouncing process.
                        this.ensureFlatIndexHierarchyOriginal(flatIndex);
                    }
                    else {
                        grid.$connector.beforeEnsureFlatIndexHierarchy(flatIndex, item);
                    }
                });
                dataProviderController.isLoadingOriginal = dataProviderController.isLoading;
                dataProviderController.isLoading = tryCatchWrapper(function () {
                    return grid.$connector.hasEnsureSubCacheQueue() || this.isLoadingOriginal();
                });
                var rootPageCallbacks = {};
                var treePageCallbacks = {};
                var cache = {};
                /* parentRequestDelay - optimizes parent requests by batching several requests
                 *  into one request. Delay in milliseconds. Disable by setting to 0.
                 *  parentRequestBatchMaxSize - maximum size of the batch.
                 */
                var parentRequestDelay = 50;
                var parentRequestBatchMaxSize = 20;
                var parentRequestQueue = [];
                var parentRequestDebouncer;
                var ensureSubCacheQueue = [];
                var ensureSubCacheDebouncer;
                var rootRequestDelay = 150;
                var rootRequestDebouncer;
                var lastRequestedRanges = {};
                var root = 'null';
                lastRequestedRanges[root] = [0, 0];
                var currentUpdateClearRange = null;
                var currentUpdateSetRange = null;
                var validSelectionModes = ['SINGLE', 'NONE', 'MULTI'];
                var selectedKeys = {};
                var selectionMode = 'SINGLE';
                var sorterDirectionsSetFromServer = false;
                grid.size = 0; // To avoid NaN here and there before we get proper data
                grid.itemIdPath = 'key';
                function createEmptyItemFromKey(key) {
                    var _a;
                    return _a = {}, _a[grid.itemIdPath] = key, _a;
                }
                grid.$connector = {};
                grid.$connector.hasCacheForParentKey = tryCatchWrapper(function (parentKey) { var _a; return ((_a = cache[parentKey]) === null || _a === void 0 ? void 0 : _a.size) !== undefined; });
                grid.$connector.hasEnsureSubCacheQueue = tryCatchWrapper(function () { return ensureSubCacheQueue.length > 0; });
                grid.$connector.hasParentRequestQueue = tryCatchWrapper(function () { return parentRequestQueue.length > 0; });
                grid.$connector.hasRootRequestQueue = tryCatchWrapper(function () {
                    return Object.keys(rootPageCallbacks).length > 0 || !!(rootRequestDebouncer === null || rootRequestDebouncer === void 0 ? void 0 : rootRequestDebouncer.isActive());
                });
                grid.$connector.beforeEnsureFlatIndexHierarchy = tryCatchWrapper(function (flatIndex, item) {
                    // add call to queue
                    ensureSubCacheQueue.push({
                        flatIndex: flatIndex,
                        itemkey: grid.getItemId(item)
                    });
                    ensureSubCacheDebouncer = debounce_js_1.Debouncer.debounce(ensureSubCacheDebouncer, async_js_1.animationFrame, function () {
                        while (ensureSubCacheQueue.length) {
                            grid.$connector.flushEnsureSubCache();
                        }
                    });
                });
                grid.$connector.doSelection = tryCatchWrapper(function (items, userOriginated) {
                    if (selectionMode === 'NONE' || !items.length || (userOriginated && grid.hasAttribute('disabled'))) {
                        return;
                    }
                    if (selectionMode === 'SINGLE') {
                        selectedKeys = {};
                    }
                    items.forEach(function (item) {
                        if (item) {
                            selectedKeys[item.key] = item;
                            item.selected = true;
                            if (userOriginated) {
                                grid.$server.select(item.key);
                            }
                        }
                        // FYI: In single selection mode, the server can send items = [null]
                        // which means a "Deselect All" command.
                        var isSelectedItemDifferentOrNull = !grid.activeItem || !item || item.key != grid.activeItem.key;
                        if (!userOriginated && selectionMode === 'SINGLE' && isSelectedItemDifferentOrNull) {
                            grid.activeItem = item;
                        }
                    });
                    grid.selectedItems = Object.values(selectedKeys);
                });
                grid.$connector.doDeselection = tryCatchWrapper(function (items, userOriginated) {
                    if (selectionMode === 'NONE' || !items.length || (userOriginated && grid.hasAttribute('disabled'))) {
                        return;
                    }
                    var updatedSelectedItems = grid.selectedItems.slice();
                    while (items.length) {
                        var itemToDeselect = items.shift();
                        for (var i = 0; i < updatedSelectedItems.length; i++) {
                            var selectedItem = updatedSelectedItems[i];
                            if ((itemToDeselect === null || itemToDeselect === void 0 ? void 0 : itemToDeselect.key) === selectedItem.key) {
                                updatedSelectedItems.splice(i, 1);
                                break;
                            }
                        }
                        if (itemToDeselect) {
                            delete selectedKeys[itemToDeselect.key];
                            delete itemToDeselect.selected;
                            if (userOriginated) {
                                grid.$server.deselect(itemToDeselect.key);
                            }
                        }
                    }
                    grid.selectedItems = updatedSelectedItems;
                });
                grid.__activeItemChanged = tryCatchWrapper(function (newVal, oldVal) {
                    if (selectionMode != 'SINGLE') {
                        return;
                    }
                    if (!newVal) {
                        if (oldVal && selectedKeys[oldVal.key]) {
                            if (grid.__deselectDisallowed) {
                                grid.activeItem = oldVal;
                            }
                            else {
                                grid.$connector.doDeselection([oldVal], true);
                            }
                        }
                    }
                    else if (!selectedKeys[newVal.key]) {
                        grid.$connector.doSelection([newVal], true);
                    }
                });
                grid._createPropertyObserver('activeItem', '__activeItemChanged', true);
                grid.__activeItemChangedDetails = tryCatchWrapper(function (newVal, oldVal) {
                    if (grid.__disallowDetailsOnClick) {
                        return;
                    }
                    // when grid is attached, newVal is not set and oldVal is undefined
                    // do nothing
                    if (newVal == null && oldVal === undefined) {
                        return;
                    }
                    if (newVal && !newVal.detailsOpened) {
                        grid.$server.setDetailsVisible(newVal.key);
                    }
                    else {
                        grid.$server.setDetailsVisible(null);
                    }
                });
                grid._createPropertyObserver('activeItem', '__activeItemChangedDetails', true);
                grid.$connector._getSameLevelPage = tryCatchWrapper(function (parentKey, currentCache, currentCacheItemIndex) {
                    var currentParentKey = currentCache.parentItem ? grid.getItemId(currentCache.parentItem) : root;
                    if (currentParentKey === parentKey) {
                        // Level match found, return the page number.
                        return Math.floor(currentCacheItemIndex / grid.pageSize);
                    }
                    var parentCache = currentCache.parentCache, parentCacheIndex = currentCache.parentCacheIndex;
                    if (!parentCache) {
                        // There is no parent cache to match level
                        return null;
                    }
                    // Traverse the tree upwards until a match is found or the end is reached
                    return this._getSameLevelPage(parentKey, parentCache, parentCacheIndex);
                });
                grid.$connector.flushEnsureSubCache = tryCatchWrapper(function () {
                    var pendingFetch = ensureSubCacheQueue.shift();
                    if (pendingFetch) {
                        dataProviderController.ensureFlatIndexHierarchyOriginal(pendingFetch.flatIndex);
                        return true;
                    }
                    return false;
                });
                grid.$connector.debounceRootRequest = tryCatchWrapper(function (page) {
                    var delay = grid._hasData ? rootRequestDelay : 0;
                    rootRequestDebouncer = debounce_js_1.Debouncer.debounce(rootRequestDebouncer, async_js_1.timeOut.after(delay), function () {
                        grid.$connector.fetchPage(function (firstIndex, size) { return grid.$server.setRequestedRange(firstIndex, size); }, page, root);
                    });
                });
                grid.$connector.flushParentRequests = tryCatchWrapper(function () {
                    var pendingFetches = [];
                    parentRequestQueue.splice(0, parentRequestBatchMaxSize).forEach(function (_a) {
                        var parentKey = _a.parentKey, page = _a.page;
                        grid.$connector.fetchPage(function (firstIndex, size) { return pendingFetches.push({ parentKey: parentKey, firstIndex: firstIndex, size: size }); }, page, parentKey);
                    });
                    if (pendingFetches.length) {
                        grid.$server.setParentRequestedRanges(pendingFetches);
                    }
                });
                grid.$connector.debounceParentRequest = tryCatchWrapper(function (parentKey, page) {
                    // Remove any pending requests for the same parentKey.
                    parentRequestQueue = parentRequestQueue.filter(function (request) { return request.parentKey !== parentKey; });
                    // Add the new request to the queue.
                    parentRequestQueue.push({ parentKey: parentKey, page: page });
                    // Debounce the request to avoid sending multiple requests for the same parentKey.
                    parentRequestDebouncer = debounce_js_1.Debouncer.debounce(parentRequestDebouncer, async_js_1.timeOut.after(parentRequestDelay), function () {
                        while (parentRequestQueue.length) {
                            grid.$connector.flushParentRequests();
                        }
                    });
                });
                grid.$connector.fetchPage = tryCatchWrapper(function (fetch, page, parentKey) {
                    var _a, _b;
                    // Adjust the requested page to be within the valid range in case
                    // the grid size has changed while fetchPage was debounced.
                    if (parentKey === root) {
                        page = Math.min(page, Math.floor((grid.size - 1) / grid.pageSize));
                    }
                    // Determine what to fetch based on scroll position and not only
                    // what grid asked for
                    var visibleRows = grid._getRenderedRows();
                    var start = visibleRows.length > 0 ? visibleRows[0].index : 0;
                    var end = visibleRows.length > 0 ? visibleRows[visibleRows.length - 1].index : 0;
                    // The buffer size could be multiplied by some constant defined by the user,
                    // if he needs to reduce the number of items sent to the Grid to improve performance
                    // or to increase it to make Grid smoother when scrolling
                    var buffer = end - start;
                    var firstNeededIndex = Math.max(0, start - buffer);
                    var lastNeededIndex = Math.min(end + buffer, grid._flatSize);
                    var pageRange = [null, null];
                    for (var idx = firstNeededIndex; idx <= lastNeededIndex; idx++) {
                        var _c = dataProviderController.getFlatIndexContext(idx), cache_1 = _c.cache, index = _c.index;
                        // Try to match level by going up in hierarchy. The page range should include
                        // pages that contain either of the following:
                        //   - visible items of the current cache
                        //   - same level parents of visible descendant items
                        // If the parent items are not considered, Flow would remove the hidden parent
                        // items from the current level cache. This can lead to an infinite loop when using
                        // scrollToIndex feature.
                        var sameLevelPage = grid.$connector._getSameLevelPage(parentKey, cache_1, index);
                        if (sameLevelPage === null) {
                            continue;
                        }
                        pageRange[0] = Math.min((_a = pageRange[0]) !== null && _a !== void 0 ? _a : sameLevelPage, sameLevelPage);
                        pageRange[1] = Math.max((_b = pageRange[1]) !== null && _b !== void 0 ? _b : sameLevelPage, sameLevelPage);
                    }
                    // When the viewport doesn't contain the requested page or it doesn't contain any items from
                    // the requested level at all, it means that the scroll position has changed while fetchPage
                    // was debounced. For example, it can happen if the user scrolls the grid to the bottom and
                    // then immediately back to the top. In this case, the request for the last page will be left
                    // hanging. To avoid this, as a workaround, we reset the range to only include the requested page
                    // to make sure all hanging requests are resolved. After that, the grid requests the first page
                    // or whatever in the viewport again.
                    if (pageRange.some(function (p) { return p === null; }) || page < pageRange[0] || page > pageRange[1]) {
                        pageRange = [page, page];
                    }
                    var lastRequestedRange = lastRequestedRanges[parentKey] || [-1, -1];
                    if (lastRequestedRange[0] != pageRange[0] || lastRequestedRange[1] != pageRange[1]) {
                        lastRequestedRanges[parentKey] = pageRange;
                        var pageCount = pageRange[1] - pageRange[0] + 1;
                        fetch(pageRange[0] * grid.pageSize, pageCount * grid.pageSize);
                    }
                });
                grid.dataProvider = tryCatchWrapper(function (params, callback) {
                    var _a, _b;
                    if (params.pageSize != grid.pageSize) {
                        throw 'Invalid pageSize';
                    }
                    var page = params.page;
                    if (params.parentItem) {
                        var parentUniqueKey = grid.getItemId(params.parentItem);
                        if (!treePageCallbacks[parentUniqueKey]) {
                            treePageCallbacks[parentUniqueKey] = {};
                        }
                        var parentItemContext = dataProviderController.getItemContext(params.parentItem);
                        if (((_a = cache[parentUniqueKey]) === null || _a === void 0 ? void 0 : _a[page]) && parentItemContext.subCache) {
                            // workaround: sometimes grid-element gives page index that overflows
                            page = Math.min(page, Math.floor(cache[parentUniqueKey].size / grid.pageSize));
                            // Ensure grid isn't in loading state when the callback executes
                            ensureSubCacheQueue = [];
                            // Resolve the callback from cache
                            callback(cache[parentUniqueKey][page], cache[parentUniqueKey].size);
                        }
                        else {
                            treePageCallbacks[parentUniqueKey][page] = callback;
                            grid.$connector.debounceParentRequest(parentUniqueKey, page);
                        }
                    }
                    else {
                        // workaround: sometimes grid-element gives page index that overflows
                        page = Math.min(page, Math.floor(grid.size / grid.pageSize));
                        // size is controlled by the server (data communicator), so if the
                        // size is zero, we know that there is no data to fetch.
                        // This also prevents an empty grid getting stuck in a loading state.
                        // The connector does not cache empty pages, so if the grid requests
                        // data again, there would be no cache entry, causing a request to
                        // the server. However, the data communicator will never respond,
                        // as it assumes that the data is already cached.
                        if (grid.size === 0) {
                            callback([], 0);
                            return;
                        }
                        if ((_b = cache[root]) === null || _b === void 0 ? void 0 : _b[page]) {
                            callback(cache[root][page]);
                        }
                        else {
                            rootPageCallbacks[page] = callback;
                            grid.$connector.debounceRootRequest(page);
                        }
                    }
                });
                grid.$connector.setSorterDirections = tryCatchWrapper(function (directions) {
                    sorterDirectionsSetFromServer = true;
                    setTimeout(tryCatchWrapper(function () {
                        try {
                            var sorters_1 = Array.from(grid.querySelectorAll('vaadin-grid-sorter'));
                            // Sorters for hidden columns are removed from DOM but stored in the web component.
                            // We need to ensure that all the sorters are reset when using `grid.sort(null)`.
                            grid._sorters.forEach(function (sorter) {
                                if (!sorters_1.includes(sorter)) {
                                    sorters_1.push(sorter);
                                }
                            });
                            sorters_1.forEach(function (sorter) {
                                sorter.direction = null;
                            });
                            // Apply directions in correct order, depending on configured multi-sort priority.
                            // For the default "prepend" mode, directions need to be applied in reverse, in
                            // order for the sort indicators to match the order on the server. For "append"
                            // just keep the order passed from the server.
                            if (grid.multiSortPriority !== 'append') {
                                directions = directions.reverse();
                            }
                            directions.forEach(function (_a) {
                                var column = _a.column, direction = _a.direction;
                                sorters_1.forEach(function (sorter) {
                                    if (sorter.getAttribute('path') === column) {
                                        sorter.direction = direction;
                                    }
                                });
                            });
                        }
                        finally {
                            sorterDirectionsSetFromServer = false;
                        }
                    }));
                });
                grid._updateItem = tryCatchWrapper(function (row, item) {
                    vaadin_grid_js_1.Grid.prototype._updateItem.call(grid, row, item);
                    // There might be inactive component renderers on hidden rows that still refer to the
                    // same component instance as one of the renderers on a visible row. Making the
                    // inactive/hidden renderer attach the component might steal it from a visible/active one.
                    if (!row.hidden) {
                        // make sure that component renderers are updated
                        Array.from(row.children).forEach(function (cell) {
                            var _a, _b;
                            Array.from(((_b = (_a = cell === null || cell === void 0 ? void 0 : cell._content) === null || _a === void 0 ? void 0 : _a.__templateInstance) === null || _b === void 0 ? void 0 : _b.children) || []).forEach(function (content) {
                                if (content._attachRenderedComponentIfAble) {
                                    content._attachRenderedComponentIfAble();
                                }
                                // In hierarchy column of tree grid, the component renderer is inside its content,
                                // this updates it renderer from innerContent
                                Array.from((content === null || content === void 0 ? void 0 : content.children) || []).forEach(function (innerContent) {
                                    if (innerContent._attachRenderedComponentIfAble) {
                                        innerContent._attachRenderedComponentIfAble();
                                    }
                                });
                            });
                        });
                    }
                    // since no row can be selected when selection mode is NONE
                    // if selectionMode is set to NONE, remove aria-selected attribute from the row
                    if (selectionMode === validSelectionModes[1]) {
                        // selectionMode === NONE
                        row.removeAttribute('aria-selected');
                        Array.from(row.children).forEach(function (cell) { return cell.removeAttribute('aria-selected'); });
                    }
                });
                var itemExpandedChanged = tryCatchWrapper(function (item, expanded) {
                    // method available only for the TreeGrid server-side component
                    if (item == undefined || grid.$server.updateExpandedState == undefined) {
                        return;
                    }
                    var parentKey = grid.getItemId(item);
                    grid.$server.updateExpandedState(parentKey, expanded);
                });
                // Patch grid.expandItem and grid.collapseItem to have
                // itemExpandedChanged run when either happens.
                grid.expandItem = tryCatchWrapper(function (item) {
                    itemExpandedChanged(item, true);
                    vaadin_grid_js_1.Grid.prototype.expandItem.call(grid, item);
                });
                grid.collapseItem = tryCatchWrapper(function (item) {
                    itemExpandedChanged(item, false);
                    vaadin_grid_js_1.Grid.prototype.collapseItem.call(grid, item);
                });
                var itemsUpdated = function (items) {
                    var _a;
                    if (!items || !Array.isArray(items)) {
                        throw 'Attempted to call itemsUpdated with an invalid value: ' + JSON.stringify(items);
                    }
                    var detailsOpenedItems = Array.from(grid.detailsOpenedItems);
                    var updatedSelectedItem = false;
                    for (var i = 0; i < items.length; ++i) {
                        var item = items[i];
                        if (!item) {
                            continue;
                        }
                        if (item.detailsOpened) {
                            if (grid._getItemIndexInArray(item, detailsOpenedItems) < 0) {
                                detailsOpenedItems.push(item);
                            }
                        }
                        else if (grid._getItemIndexInArray(item, detailsOpenedItems) >= 0) {
                            detailsOpenedItems.splice(grid._getItemIndexInArray(item, detailsOpenedItems), 1);
                        }
                        if (selectedKeys[item.key]) {
                            selectedKeys[item.key] = item;
                            item.selected = true;
                            updatedSelectedItem = true;
                        }
                    }
                    grid.detailsOpenedItems = detailsOpenedItems;
                    if (updatedSelectedItem) {
                        // Replace the objects in the grid.selectedItems array without replacing the array
                        // itself in order to avoid an unnecessary re-render of the grid.
                        (_a = grid.selectedItems).splice.apply(_a, __spreadArray([0, grid.selectedItems.length], Object.values(selectedKeys), false));
                    }
                };
                /**
                 * Updates the cache for the given page for grid or tree-grid.
                 *
                 * @param page index of the page to update
                 * @param parentKey the key of the parent item for the page
                 * @returns an array of the updated items for the page, or undefined if no items were cached for the page
                 */
                var updateGridCache = function (page, parentKey) {
                    var items;
                    if ((parentKey || root) !== root) {
                        items = cache[parentKey][page];
                        var parentItem = createEmptyItemFromKey(parentKey);
                        var parentItemContext = dataProviderController.getItemContext(parentItem);
                        if (parentItemContext && parentItemContext.subCache) {
                            var callbacksForParentKey = treePageCallbacks[parentKey];
                            var callback = callbacksForParentKey && callbacksForParentKey[page];
                            _updateGridCache(page, items, callback, parentItemContext.subCache);
                        }
                    }
                    else {
                        items = cache[root][page];
                        _updateGridCache(page, items, rootPageCallbacks[page], dataProviderController.rootCache);
                    }
                    return items;
                };
                var _updateGridCache = function (page, items, callback, levelcache) {
                    // Force update unless there's a callback waiting
                    if (!callback) {
                        var rangeStart = page * grid.pageSize;
                        var rangeEnd = rangeStart + grid.pageSize;
                        if (!items) {
                            if (levelcache && levelcache.items) {
                                for (var idx = rangeStart; idx < rangeEnd; idx++) {
                                    delete levelcache.items[idx];
                                }
                            }
                        }
                        else {
                            if (levelcache && levelcache.items) {
                                for (var idx = rangeStart; idx < rangeEnd; idx++) {
                                    if (levelcache.items[idx]) {
                                        levelcache.items[idx] = items[idx - rangeStart];
                                    }
                                }
                            }
                        }
                    }
                };
                /**
                 * Updates all visible grid rows in DOM.
                 */
                var updateAllGridRowsInDomBasedOnCache = function () {
                    updateGridFlatSize();
                    grid.__updateVisibleRows();
                };
                /**
                 * Updates the <vaadin-grid>'s internal cache size and flat size.
                 */
                var updateGridFlatSize = function () {
                    dataProviderController.recalculateFlatSize();
                    grid._flatSize = dataProviderController.flatSize;
                };
                /**
                 * Update the given items in DOM if currently visible.
                 *
                 * @param array items the items to update in DOM
                 */
                var updateGridItemsInDomBasedOnCache = function (items) {
                    if (!items || !grid.$ || grid.$.items.childElementCount === 0) {
                        return;
                    }
                    var itemKeys = items.map(function (item) { return item.key; });
                    var indexes = grid
                        ._getRenderedRows()
                        .filter(function (row) { return row._item && itemKeys.includes(row._item.key); })
                        .map(function (row) { return row.index; });
                    if (indexes.length > 0) {
                        grid.__updateVisibleRows(indexes[0], indexes[indexes.length - 1]);
                    }
                };
                grid.$connector.set = tryCatchWrapper(function (index, items, parentKey) {
                    if (index % grid.pageSize != 0) {
                        throw 'Got new data to index ' + index + ' which is not aligned with the page size of ' + grid.pageSize;
                    }
                    var pkey = parentKey || root;
                    var firstPage = index / grid.pageSize;
                    var updatedPageCount = Math.ceil(items.length / grid.pageSize);
                    // For root cache, remember the range of pages that were set during an update
                    if (pkey === root) {
                        currentUpdateSetRange = [firstPage, firstPage + updatedPageCount - 1];
                    }
                    for (var i = 0; i < updatedPageCount; i++) {
                        var page = firstPage + i;
                        var slice = items.slice(i * grid.pageSize, (i + 1) * grid.pageSize);
                        if (!cache[pkey]) {
                            cache[pkey] = {};
                        }
                        cache[pkey][page] = slice;
                        grid.$connector.doSelection(slice.filter(function (item) { return item.selected; }));
                        grid.$connector.doDeselection(slice.filter(function (item) { return !item.selected && selectedKeys[item.key]; }));
                        var updatedItems = updateGridCache(page, pkey);
                        if (updatedItems) {
                            itemsUpdated(updatedItems);
                            updateGridItemsInDomBasedOnCache(updatedItems);
                        }
                    }
                });
                var itemToCacheLocation = function (item) {
                    var parent = item.parentUniqueKey || root;
                    if (cache[parent]) {
                        for (var page in cache[parent]) {
                            for (var index in cache[parent][page]) {
                                if (grid.getItemId(cache[parent][page][index]) === grid.getItemId(item)) {
                                    return { page: page, index: index, parentKey: parent };
                                }
                            }
                        }
                    }
                    return null;
                };
                /**
                 * Updates the given items for a hierarchical grid.
                 *
                 * @param updatedItems the updated items array
                 */
                grid.$connector.updateHierarchicalData = tryCatchWrapper(function (updatedItems) {
                    var pagesToUpdate = [];
                    // locate and update the items in cache
                    // find pages that need updating
                    for (var i = 0; i < updatedItems.length; i++) {
                        var cacheLocation = itemToCacheLocation(updatedItems[i]);
                        if (cacheLocation) {
                            cache[cacheLocation.parentKey][cacheLocation.page][cacheLocation.index] = updatedItems[i];
                            var key = cacheLocation.parentKey + ':' + cacheLocation.page;
                            if (!pagesToUpdate[key]) {
                                pagesToUpdate[key] = {
                                    parentKey: cacheLocation.parentKey,
                                    page: cacheLocation.page
                                };
                            }
                        }
                    }
                    // IE11 doesn't work with the transpiled version of the forEach.
                    var keys = Object.keys(pagesToUpdate);
                    for (var i = 0; i < keys.length; i++) {
                        var pageToUpdate = pagesToUpdate[keys[i]];
                        var affectedUpdatedItems = updateGridCache(pageToUpdate.page, pageToUpdate.parentKey);
                        if (affectedUpdatedItems) {
                            itemsUpdated(affectedUpdatedItems);
                            updateGridItemsInDomBasedOnCache(affectedUpdatedItems);
                        }
                    }
                });
                /**
                 * Updates the given items for a non-hierarchical grid.
                 *
                 * @param updatedItems the updated items array
                 */
                grid.$connector.updateFlatData = tryCatchWrapper(function (updatedItems) {
                    // update (flat) caches
                    for (var i = 0; i < updatedItems.length; i++) {
                        var cacheLocation = itemToCacheLocation(updatedItems[i]);
                        if (cacheLocation) {
                            // update connector cache
                            cache[cacheLocation.parentKey][cacheLocation.page][cacheLocation.index] = updatedItems[i];
                            // update grid's cache
                            var index = parseInt(cacheLocation.page) * grid.pageSize + parseInt(cacheLocation.index);
                            var rootCache = dataProviderController.rootCache;
                            if (rootCache.items[index]) {
                                rootCache.items[index] = updatedItems[i];
                            }
                        }
                    }
                    itemsUpdated(updatedItems);
                    updateGridItemsInDomBasedOnCache(updatedItems);
                });
                grid.$connector.clearExpanded = tryCatchWrapper(function () {
                    grid.expandedItems = [];
                    ensureSubCacheQueue = [];
                    parentRequestQueue = [];
                });
                /**
                 * Ensures that the last requested page range does not include pages for data that has been cleared.
                 * The last requested range is used in `fetchPage` to skip requests to the server if the page range didn't
                 * change. However, if some pages of that range have been cleared by data communicator, we need to clear the
                 * range to ensure the pages get loaded again. This can happen for example when changing the requested range
                 * on the server (e.g. preload of items on scroll to index), which can cause data communicator to clear pages
                 * that the connector assumes are already loaded.
                 */
                var sanitizeLastRequestedRange = function () {
                    // Only relevant for the root cache
                    var range = lastRequestedRanges[root];
                    // Range may not be set yet, or nothing was cleared
                    if (!range || !currentUpdateClearRange) {
                        return;
                    }
                    // Determine all pages that were cleared
                    var numClearedPages = currentUpdateClearRange[1] - currentUpdateClearRange[0] + 1;
                    var clearedPages = Array.from({ length: numClearedPages }, function (_, i) { return currentUpdateClearRange[0] + i; });
                    // Remove pages that have been set in same update
                    if (currentUpdateSetRange) {
                        var first = currentUpdateSetRange[0], last = currentUpdateSetRange[1];
                        for (var page = first; page <= last; page++) {
                            var index = clearedPages.indexOf(page);
                            if (index >= 0) {
                                clearedPages.splice(index, 1);
                            }
                        }
                    }
                    // Clear the last requested range if it includes any of the cleared pages
                    if (clearedPages.some(function (page) { return page >= range[0] && page <= range[1]; })) {
                        range[0] = -1;
                        range[1] = -1;
                    }
                };
                grid.$connector.clear = tryCatchWrapper(function (index, length, parentKey) {
                    var pkey = parentKey || root;
                    if (!cache[pkey] || Object.keys(cache[pkey]).length === 0) {
                        return;
                    }
                    if (index % grid.pageSize != 0) {
                        throw ('Got cleared data for index ' + index + ' which is not aligned with the page size of ' + grid.pageSize);
                    }
                    var firstPage = Math.floor(index / grid.pageSize);
                    var updatedPageCount = Math.ceil(length / grid.pageSize);
                    // For root cache, remember the range of pages that were cleared during an update
                    if (pkey === root) {
                        currentUpdateClearRange = [firstPage, firstPage + updatedPageCount - 1];
                    }
                    for (var i = 0; i < updatedPageCount; i++) {
                        var page = firstPage + i;
                        var items = cache[pkey][page];
                        grid.$connector.doDeselection(items.filter(function (item) { return selectedKeys[item.key]; }));
                        items.forEach(function (item) { return grid.closeItemDetails(item); });
                        delete cache[pkey][page];
                        var updatedItems = updateGridCache(page, parentKey);
                        if (updatedItems) {
                            itemsUpdated(updatedItems);
                        }
                        updateGridItemsInDomBasedOnCache(items);
                    }
                    var cacheToClear = dataProviderController.rootCache;
                    if (parentKey) {
                        var parentItem = createEmptyItemFromKey(pkey);
                        var parentItemContext = dataProviderController.getItemContext(parentItem);
                        cacheToClear = parentItemContext.subCache;
                    }
                    var endIndex = index + updatedPageCount * grid.pageSize;
                    for (var itemIndex = index; itemIndex < endIndex; itemIndex++) {
                        delete cacheToClear.items[itemIndex];
                        cacheToClear.removeSubCache(itemIndex);
                    }
                    updateGridFlatSize();
                });
                grid.$connector.reset = tryCatchWrapper(function () {
                    grid.size = 0;
                    deleteObjectContents(cache);
                    deleteObjectContents(dataProviderController.rootCache.items);
                    deleteObjectContents(lastRequestedRanges);
                    if (ensureSubCacheDebouncer) {
                        ensureSubCacheDebouncer.cancel();
                    }
                    if (parentRequestDebouncer) {
                        parentRequestDebouncer.cancel();
                    }
                    if (rootRequestDebouncer) {
                        rootRequestDebouncer.cancel();
                    }
                    ensureSubCacheDebouncer = undefined;
                    parentRequestDebouncer = undefined;
                    ensureSubCacheQueue = [];
                    parentRequestQueue = [];
                    updateAllGridRowsInDomBasedOnCache();
                });
                var deleteObjectContents = function (obj) { return Object.keys(obj).forEach(function (key) { return delete obj[key]; }); };
                grid.$connector.updateSize = function (newSize) { return (grid.size = newSize); };
                grid.$connector.updateUniqueItemIdPath = function (path) { return (grid.itemIdPath = path); };
                grid.$connector.expandItems = tryCatchWrapper(function (items) {
                    var newExpandedItems = Array.from(grid.expandedItems);
                    items.filter(function (item) { return !grid._isExpanded(item); }).forEach(function (item) { return newExpandedItems.push(item); });
                    grid.expandedItems = newExpandedItems;
                });
                grid.$connector.collapseItems = tryCatchWrapper(function (items) {
                    var newExpandedItems = Array.from(grid.expandedItems);
                    items.forEach(function (item) {
                        var index = grid._getItemIndexInArray(item, newExpandedItems);
                        if (index >= 0) {
                            newExpandedItems.splice(index, 1);
                        }
                    });
                    grid.expandedItems = newExpandedItems;
                    items.forEach(function (item) { return grid.$connector.removeFromQueue(item); });
                });
                grid.$connector.removeFromQueue = tryCatchWrapper(function (item) {
                    var itemId = grid.getItemId(item);
                    // The treePageCallbacks for the itemId are about to be discarded ->
                    // Resolve the callbacks with an empty array to not leave grid in loading state
                    Object.values(treePageCallbacks[itemId] || {}).forEach(function (callback) { return callback([]); });
                    delete treePageCallbacks[itemId];
                    ensureSubCacheQueue = ensureSubCacheQueue.filter(function (item) { return item.itemkey !== itemId; });
                    parentRequestQueue = parentRequestQueue.filter(function (item) { return item.parentKey !== itemId; });
                });
                grid.$connector.confirmParent = tryCatchWrapper(function (id, parentKey, levelSize) {
                    // Create connector cache if it doesn't exist
                    if (!cache[parentKey]) {
                        cache[parentKey] = {};
                    }
                    // Update connector cache size
                    var hasSizeChanged = cache[parentKey].size !== levelSize;
                    cache[parentKey].size = levelSize;
                    if (levelSize === 0) {
                        cache[parentKey][0] = [];
                    }
                    // If grid has outstanding requests for this parent, then resolve them
                    // and let grid update the flat size and re-render.
                    var outstandingRequests = Object.getOwnPropertyNames(treePageCallbacks[parentKey] || {});
                    for (var i = 0; i < outstandingRequests.length; i++) {
                        var page = outstandingRequests[i];
                        var lastRequestedRange = lastRequestedRanges[parentKey] || [0, 0];
                        var callback = treePageCallbacks[parentKey][page];
                        if ((cache[parentKey] && cache[parentKey][page]) ||
                            page < lastRequestedRange[0] ||
                            page > lastRequestedRange[1]) {
                            delete treePageCallbacks[parentKey][page];
                            var items = cache[parentKey][page] || new Array(levelSize);
                            callback(items, levelSize);
                        }
                        else if (callback && levelSize === 0) {
                            // The parent item has 0 child items => resolve the callback with an empty array
                            delete treePageCallbacks[parentKey][page];
                            callback([], levelSize);
                        }
                    }
                    // If size has changed, and there are no outstanding requests, then
                    // manually update the size of the grid cache and update the effective
                    // size, effectively re-rendering the grid. This is necessary when
                    // individual items are refreshed on the server, in which case there
                    // is no loading request from the grid itself. In that case, if
                    // children were added or removed, the grid will not be aware of it
                    // unless we manually update the size.
                    if (hasSizeChanged && outstandingRequests.length === 0) {
                        var parentItem = createEmptyItemFromKey(parentKey);
                        var parentItemContext = dataProviderController.getItemContext(parentItem);
                        if (parentItemContext && parentItemContext.subCache) {
                            parentItemContext.subCache.size = levelSize;
                        }
                        updateGridFlatSize();
                    }
                    // Let server know we're done
                    grid.$server.confirmParentUpdate(id, parentKey);
                    if (!grid.loading) {
                        grid.__confirmParentUpdateDebouncer = debounce_js_1.Debouncer.debounce(grid.__confirmParentUpdateDebouncer, async_js_1.animationFrame, function () { return grid.__updateVisibleRows(); });
                    }
                });
                grid.$connector.confirm = tryCatchWrapper(function (id) {
                    var _a;
                    // We're done applying changes from this batch, resolve outstanding
                    // callbacks
                    var outstandingRequests = Object.getOwnPropertyNames(rootPageCallbacks);
                    for (var i = 0; i < outstandingRequests.length; i++) {
                        var page = outstandingRequests[i];
                        var lastRequestedRange = lastRequestedRanges[root] || [0, 0];
                        var lastAvailablePage = grid.size ? Math.ceil(grid.size / grid.pageSize) - 1 : 0;
                        // It's possible that the lastRequestedRange includes a page that's beyond lastAvailablePage if the grid's size got reduced during an ongoing data request
                        var lastRequestedRangeEnd = Math.min(lastRequestedRange[1], lastAvailablePage);
                        // Resolve if we have data or if we don't expect to get data
                        var callback = rootPageCallbacks[page];
                        if (((_a = cache[root]) === null || _a === void 0 ? void 0 : _a[page]) || page < lastRequestedRange[0] || +page > lastRequestedRangeEnd) {
                            delete rootPageCallbacks[page];
                            if (cache[root][page]) {
                                // Cached data is available, resolve the callback
                                callback(cache[root][page]);
                            }
                            else {
                                // No cached data, resolve the callback with an empty array
                                callback(new Array(grid.pageSize));
                                // Request grid for content update
                                grid.requestContentUpdate();
                            }
                        }
                        else if (callback && grid.size === 0) {
                            // The grid has 0 items => resolve the callback with an empty array
                            delete rootPageCallbacks[page];
                            callback([]);
                        }
                    }
                    // Sanitize last requested range for the root level
                    sanitizeLastRequestedRange();
                    // Clear current update state
                    currentUpdateSetRange = null;
                    currentUpdateClearRange = null;
                    // Let server know we're done
                    grid.$server.confirmUpdate(id);
                });
                grid.$connector.ensureHierarchy = tryCatchWrapper(function () {
                    for (var parentKey in cache) {
                        if (parentKey !== root) {
                            delete cache[parentKey];
                        }
                    }
                    deleteObjectContents(lastRequestedRanges);
                    dataProviderController.rootCache.removeSubCaches();
                    updateAllGridRowsInDomBasedOnCache();
                });
                grid.$connector.setSelectionMode = tryCatchWrapper(function (mode) {
                    if ((typeof mode === 'string' || mode instanceof String) && validSelectionModes.indexOf(mode) >= 0) {
                        selectionMode = mode;
                        selectedKeys = {};
                        grid.$connector.updateMultiSelectable();
                    }
                    else {
                        throw 'Attempted to set an invalid selection mode';
                    }
                });
                /*
                 * Manage aria-multiselectable attribute depending on the selection mode.
                 * see more: https://github.com/vaadin/web-components/issues/1536
                 * or: https://www.w3.org/TR/wai-aria-1.1/#aria-multiselectable
                 * For selection mode SINGLE, set the aria-multiselectable attribute to false
                 */
                grid.$connector.updateMultiSelectable = tryCatchWrapper(function () {
                    if (!grid.$) {
                        return;
                    }
                    if (selectionMode === validSelectionModes[0]) {
                        grid.$.table.setAttribute('aria-multiselectable', false);
                        // For selection mode NONE, remove the aria-multiselectable attribute
                    }
                    else if (selectionMode === validSelectionModes[1]) {
                        grid.$.table.removeAttribute('aria-multiselectable');
                        // For selection mode MULTI, set aria-multiselectable to true
                    }
                    else {
                        grid.$.table.setAttribute('aria-multiselectable', true);
                    }
                });
                // Have the multi-selectable state updated on attach
                grid._createPropertyObserver('isAttached', function () { return grid.$connector.updateMultiSelectable(); });
                var singleTimeRenderer = function (renderer) {
                    return function (root) {
                        if (renderer) {
                            renderer(root);
                            renderer = null;
                        }
                    };
                };
                grid.$connector.setHeaderRenderer = tryCatchWrapper(function (column, options) {
                    var content = options.content, showSorter = options.showSorter, sorterPath = options.sorterPath;
                    if (content === null) {
                        column.headerRenderer = null;
                        return;
                    }
                    column.headerRenderer = singleTimeRenderer(function (root) {
                        // Clear previous contents
                        root.innerHTML = '';
                        // Render sorter
                        var contentRoot = root;
                        if (showSorter) {
                            var sorter = document.createElement('vaadin-grid-sorter');
                            sorter.setAttribute('path', sorterPath);
                            var ariaLabel = content instanceof Node ? content.textContent : content;
                            if (ariaLabel) {
                                sorter.setAttribute('aria-label', "Sort by ".concat(ariaLabel));
                            }
                            root.appendChild(sorter);
                            // Use sorter as content root
                            contentRoot = sorter;
                        }
                        // Add content
                        if (content instanceof Node) {
                            contentRoot.appendChild(content);
                        }
                        else {
                            contentRoot.textContent = content;
                        }
                    });
                });
                grid.__applySorters = function () {
                    var sorters = grid._mapSorters();
                    var sortersChanged = JSON.stringify(grid._previousSorters) !== JSON.stringify(sorters);
                    // Update the _previousSorters in vaadin-grid-sort-mixin so that the __applySorters
                    // method in the mixin will skip calling clearCache().
                    //
                    // In Flow Grid's case, we never want to clear the cache eagerly when the sorter elements
                    // change due to one of the following reasons:
                    //
                    // 1. Sorted by user: The items in the new sort order need to be fetched from the server,
                    // and we want to avoid a heavy re-render before the updated items have actually been fetched.
                    //
                    // 2. Sorted programmatically on the server: The items in the new sort order have already
                    // been fetched and applied to the grid. The sorter element states are updated programmatically
                    // to reflect the new sort order, but there's no need to re-render the grid rows.
                    grid._previousSorters = sorters;
                    // Call the original __applySorters method in vaadin-grid-sort-mixin
                    vaadin_grid_js_1.Grid.prototype.__applySorters.call(grid);
                    if (sortersChanged && !sorterDirectionsSetFromServer) {
                        grid.$server.sortersChanged(sorters);
                    }
                };
                grid.$connector.setFooterRenderer = tryCatchWrapper(function (column, options) {
                    var content = options.content;
                    if (content === null) {
                        column.footerRenderer = null;
                        return;
                    }
                    column.footerRenderer = singleTimeRenderer(function (root) {
                        // Clear previous contents
                        root.innerHTML = '';
                        // Add content
                        if (content instanceof Node) {
                            root.appendChild(content);
                        }
                        else {
                            root.textContent = content;
                        }
                    });
                });
                grid.addEventListener('vaadin-context-menu-before-open', tryCatchWrapper(function (e) {
                    var _a = e.detail, key = _a.key, columnId = _a.columnId;
                    grid.$server.updateContextMenuTargetItem(key, columnId);
                }));
                grid.getContextMenuBeforeOpenDetail = tryCatchWrapper(function (event) {
                    var _a, _b;
                    // For `contextmenu` events, we need to access the source event,
                    // when using open on click we just use the click event itself
                    var sourceEvent = event.detail.sourceEvent || event;
                    var eventContext = grid.getEventContext(sourceEvent);
                    var key = ((_a = eventContext.item) === null || _a === void 0 ? void 0 : _a.key) || '';
                    var columnId = ((_b = eventContext.column) === null || _b === void 0 ? void 0 : _b.id) || '';
                    return { key: key, columnId: columnId };
                });
                grid.preventContextMenu = tryCatchWrapper(function (event) {
                    var isLeftClick = event.type === 'click';
                    var column = grid.getEventContext(event).column;
                    return isLeftClick && column instanceof vaadin_grid_flow_selection_column_js_1.GridFlowSelectionColumn;
                });
                grid.addEventListener('click', tryCatchWrapper(function (e) { return _fireClickEvent(e, 'item-click'); }));
                grid.addEventListener('dblclick', tryCatchWrapper(function (e) { return _fireClickEvent(e, 'item-double-click'); }));
                grid.addEventListener('column-resize', tryCatchWrapper(function (e) {
                    var cols = grid._getColumnsInOrder().filter(function (col) { return !col.hidden; });
                    cols.forEach(function (col) {
                        col.dispatchEvent(new CustomEvent('column-drag-resize'));
                    });
                    grid.dispatchEvent(new CustomEvent('column-drag-resize', {
                        detail: {
                            resizedColumnKey: e.detail.resizedColumn._flowId
                        }
                    }));
                }));
                grid.addEventListener('column-reorder', tryCatchWrapper(function (e) {
                    var columns = grid._columnTree
                        .slice(0)
                        .pop()
                        .filter(function (c) { return c._flowId; })
                        .sort(function (b, a) { return b._order - a._order; })
                        .map(function (c) { return c._flowId; });
                    grid.dispatchEvent(new CustomEvent('column-reorder-all-columns', {
                        detail: { columns: columns }
                    }));
                }));
                grid.addEventListener('cell-focus', tryCatchWrapper(function (e) {
                    var eventContext = grid.getEventContext(e);
                    var expectedSectionValues = ['header', 'body', 'footer'];
                    if (expectedSectionValues.indexOf(eventContext.section) === -1) {
                        return;
                    }
                    grid.dispatchEvent(new CustomEvent('grid-cell-focus', {
                        detail: {
                            itemKey: eventContext.item ? eventContext.item.key : null,
                            internalColumnId: eventContext.column ? eventContext.column._flowId : null,
                            section: eventContext.section
                        }
                    }));
                }));
                function _fireClickEvent(event, eventName) {
                    // Click event was handled by the component inside grid, do nothing.
                    if (event.defaultPrevented) {
                        return;
                    }
                    var target = event.target;
                    if ((0, vaadin_grid_active_item_mixin_js_1.isFocusable)(target) || target instanceof HTMLLabelElement) {
                        return;
                    }
                    var eventContext = grid.getEventContext(event);
                    var section = eventContext.section;
                    if (eventContext.item && section !== 'details') {
                        event.itemKey = eventContext.item.key;
                        // if you have a details-renderer, getEventContext().column is undefined
                        if (eventContext.column) {
                            event.internalColumnId = eventContext.column._flowId;
                        }
                        grid.dispatchEvent(new CustomEvent(eventName, { detail: event }));
                    }
                }
                grid.cellClassNameGenerator = tryCatchWrapper(function (column, rowData) {
                    var style = rowData.item.style;
                    if (!style) {
                        return;
                    }
                    return (style.row || '') + ' ' + ((column && style[column._flowId]) || '');
                });
                grid.cellPartNameGenerator = tryCatchWrapper(function (column, rowData) {
                    var part = rowData.item.part;
                    if (!part) {
                        return;
                    }
                    return (part.row || '') + ' ' + ((column && part[column._flowId]) || '');
                });
                grid.dropFilter = tryCatchWrapper(function (rowData) { return rowData.item && !rowData.item.dropDisabled; });
                grid.dragFilter = tryCatchWrapper(function (rowData) { return rowData.item && !rowData.item.dragDisabled; });
                grid.addEventListener('grid-dragstart', tryCatchWrapper(function (e) {
                    if (grid._isSelected(e.detail.draggedItems[0])) {
                        // Dragging selected (possibly multiple) items
                        if (grid.__selectionDragData) {
                            Object.keys(grid.__selectionDragData).forEach(function (type) {
                                e.detail.setDragData(type, grid.__selectionDragData[type]);
                            });
                        }
                        else {
                            (grid.__dragDataTypes || []).forEach(function (type) {
                                e.detail.setDragData(type, e.detail.draggedItems.map(function (item) { return item.dragData[type]; }).join('\n'));
                            });
                        }
                        if (grid.__selectionDraggedItemsCount > 1) {
                            e.detail.setDraggedItemsCount(grid.__selectionDraggedItemsCount);
                        }
                    }
                    else {
                        // Dragging just one (non-selected) item
                        (grid.__dragDataTypes || []).forEach(function (type) {
                            e.detail.setDragData(type, e.detail.draggedItems[0].dragData[type]);
                        });
                    }
                }));
            })(grid);
        }
    };
})();
