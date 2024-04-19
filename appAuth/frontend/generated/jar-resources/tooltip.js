"use strict";
var _a, _b;
Object.defineProperty(exports, "__esModule", { value: true });
var vaadin_tooltip_js_1 = require("@vaadin/tooltip/src/vaadin-tooltip.js");
var _window = window;
_window.Vaadin || (_window.Vaadin = {});
(_a = _window.Vaadin).Flow || (_a.Flow = {});
(_b = _window.Vaadin.Flow).tooltip || (_b.tooltip = {});
Object.assign(_window.Vaadin.Flow.tooltip, {
    setDefaultHideDelay: function (hideDelay) { return vaadin_tooltip_js_1.Tooltip.setDefaultHideDelay(hideDelay); },
    setDefaultFocusDelay: function (focusDelay) { return vaadin_tooltip_js_1.Tooltip.setDefaultFocusDelay(focusDelay); },
    setDefaultHoverDelay: function (hoverDelay) { return vaadin_tooltip_js_1.Tooltip.setDefaultHoverDelay(hoverDelay); }
});
var _c = _window.Vaadin.Flow.tooltip, defaultHideDelay = _c.defaultHideDelay, defaultFocusDelay = _c.defaultFocusDelay, defaultHoverDelay = _c.defaultHoverDelay;
if (defaultHideDelay) {
    vaadin_tooltip_js_1.Tooltip.setDefaultHideDelay(defaultHideDelay);
}
if (defaultFocusDelay) {
    vaadin_tooltip_js_1.Tooltip.setDefaultFocusDelay(defaultFocusDelay);
}
if (defaultHoverDelay) {
    vaadin_tooltip_js_1.Tooltip.setDefaultHoverDelay(defaultHoverDelay);
}
