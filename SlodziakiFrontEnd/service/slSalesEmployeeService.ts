import { SalesEmployee } from '../model/slSalesEmployee';

const axios = require('axios');

module.exports.getAllSalesEmployees = async function(): Promise<SalesEmployee[]>{
    try {
        const response = await axios.get('http://localhost:8080/api/employees/sales')

        return response.data
    } catch (e) {
        throw new Error('Could not get sales employees')
    }
}

module.exports.getSalesEmployeeById = async function( id: number ): Promise<SalesEmployee[]>{
    try {
        const response = await axios.get('http://localhost:8080/api/employees/sales/' + id)

        return response.data
    } catch (e) {
        throw new Error('Could not get sales employee')
    }
}

module.exports.createSalesEmployee = async function(salesEmployee: SalesEmployee): Promise<number>{
    try {
        const response = await axios.post('http://localhost:8080/api/employees/sales', salesEmployee)

        return response.data
    } catch (e) {
        throw new Error('Could not create sales employee')
    }

}

module.exports.updateSalesEmployee = async function( id: number ): Promise<SalesEmployee[]>{
    try {
        const response = await axios.post('http://localhost:8080/api/employees/sales/' + id)

        return response.data
    } catch (e) {
        throw new Error('Could not update sales employee')
    }

}

module.exports.deleteSalesEmployee = async function( id: number ): Promise<void>{
    try {
         await axios.delete('http://localhost:8080/api/employees/sales/' + id)

    } catch (e) {
        throw new Error('Could not delete sales employee')
    }

}