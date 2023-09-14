import { Order } from "../model/order";

module.exports.validateOrder = function ( order: Order): string {
    const oldDate = new Date(order.orderDate).getTime();
    const todaysDate = new Date().getTime();
    

    if(((todaysDate - oldDate) / (1000 *3600 * 24 * 365)) > 1){
        return "Cannot create order that is over 1 year old";
    } 


    return null;
}
