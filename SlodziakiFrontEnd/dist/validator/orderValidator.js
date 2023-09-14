"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
module.exports.validateOrder = function (order) {
    var oldDate = new Date(order.orderDate).getTime();
    var todaysDate = new Date().getTime();
    if (((todaysDate - oldDate) / (1000 * 3600 * 24 * 365)) > 1) {
        return "Cannot create order that is over 1 year old";
    }
    return null;
};
//# sourceMappingURL=orderValidator.js.map