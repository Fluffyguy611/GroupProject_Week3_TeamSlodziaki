import {DeliveryEmployees} from '../model/sl-delivery'

const axios = require('axios');


module.exports.createDeliveryEmployee = async function (DeliveryEmployee: DeliveryEmployees): Promise<number> {
    
    try{
        const response = await axios.post(`http://localhost:8080/api/employees/delivery`, DeliveryEmployee )
        
        return response.data
    } catch (e) {
        throw new Error('Could not create delivery employees')
    }
}


// module.exports.updateDeliveryEmployeeById = async function( id: number ): Promise<DeliveryEmployees> {
//     try{
//         const response = await axios.put(`http://localhost:8080/api/employees/delivery/${id}`);

//         return response.data
//     } catch (e) {
//         throw new Error('Could not update delivery employee by id')
//     }
// }


module.exports.getDeliveryEmployeeById = async function( id: number ): Promise<DeliveryEmployees> {
    try{
        const response = await axios.get(`http://localhost:8080/api/employees/delivery/${id}`);

        return response.data
    } catch (e) {
        throw new Error('Could not get delivery employee by id')
    }
}



module.exports.getAllDeliveryEmployees = async function (): Promise<DeliveryEmployees> {
    try{
        const response = await axios.get('http://localhost:8080/api/employees/delivery');// 

        return response.data
    } catch (e) {
        throw new Error('Could not get delivery employees')
    }
}



// module.exports.deleteDeliveryEmployee = async function( id: number ): Promise<DeliveryEmployees> {
//     try{
//         const response = await axios.delete('http://localhost:8080/api/employees/delivery' + id)

//         return response.data
//     } catch (e) {
//         throw new Error('Could not delete delivery employee by id')
//     }
// }






