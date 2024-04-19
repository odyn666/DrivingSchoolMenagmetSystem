"use strict";
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
Object.defineProperty(exports, "__esModule", { value: true });
/* eslint-disable no-restricted-syntax */
/* eslint-disable max-params */
var lit_1 = require("lit");
var _window = window;
_window.Vaadin = _window.Vaadin || {};
/**
 * Assigns the component a renderer function which uses Lit to render
 * the given template expression inside the render root element.
 *
 * @param component The host component to which the renderer runction is to be set
 * @param rendererName The name of the renderer function
 * @param templateExpression The content of the template literal passed to Lit for rendering.
 * @param returnChannel A channel to the server.
 * Calling it will end up invoking a handler in the server-side LitRenderer.
 * @param clientCallables A list of function names that can be called from within the template literal.
 * @param propertyNamespace LitRenderer-specific namespace for properties.
 * Needed to avoid property name collisions between renderers.
 */
_window.Vaadin.setLitRenderer = function (component, rendererName, templateExpression, returnChannel, clientCallables, propertyNamespace) {
    // Dynamically created function that renders the templateExpression
    // inside the given root element using Lit
    var renderFunction = Function("\n    \"use strict\";\n\n    const [render, html, returnChannel] = arguments;\n\n    return (root, model, itemKey) => {\n      const { item, index } = model;\n      ".concat(clientCallables
        .map(function (clientCallable) {
        // Map all the client-callables as inline functions so they can be accessed from the template literal
        return "\n          const ".concat(clientCallable, " = (...args) => {\n            if (itemKey !== undefined) {\n              returnChannel('").concat(clientCallable, "', itemKey, args[0] instanceof Event ? [] : [...args]);\n            }\n          }");
    })
        .join(''), "\n\n      render(html`").concat(templateExpression, "`, root)\n    }\n  "))(lit_1.render, lit_1.html, returnChannel);
    var renderer = function (root, _, model) {
        var item = model.item;
        // Clean up the root element of any existing content
        // (and Lit's _$litPart$ property) from other renderers
        // TODO: Remove once https://github.com/vaadin/web-components/issues/2235 is done
        if (root.__litRenderer !== renderer) {
            root.innerHTML = '';
            delete root._$litPart$;
            root.__litRenderer = renderer;
        }
        // Map a new item that only includes the properties defined by
        // this specific LitRenderer instance. The renderer instance specific
        // "propertyNamespace" prefix is stripped from the property name at this point:
        //
        // item: { key: "2", lr_3_lastName: "Tyler"}
        // ->
        // mappedItem: { lastName: "Tyler" }
        var mappedItem = {};
        for (var key in item) {
            if (key.startsWith(propertyNamespace)) {
                mappedItem[key.replace(propertyNamespace, '')] = item[key];
            }
        }
        renderFunction(root, __assign(__assign({}, model), { item: mappedItem }), item.key);
    };
    renderer.__rendererId = propertyNamespace;
    component[rendererName] = renderer;
};
/**
 * Removes the renderer function with the given name from the component
 * if the propertyNamespace matches the renderer's id.
 *
 * @param component The host component whose renderer function is to be removed
 * @param rendererName The name of the renderer function
 * @param rendererId The rendererId of the function to be removed
 */
_window.Vaadin.unsetLitRenderer = function (component, rendererName, rendererId) {
    var _a;
    // The check for __rendererId property is necessary since the renderer function
    // may get overridden by another renderer, for example, by one coming from
    // vaadin-template-renderer. We don't want LitRenderer registration cleanup to
    // unintentionally remove the new renderer.
    if (((_a = component[rendererName]) === null || _a === void 0 ? void 0 : _a.__rendererId) === rendererId) {
        component[rendererName] = undefined;
    }
};
