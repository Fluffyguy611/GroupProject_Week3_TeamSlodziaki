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

module.exports.createSalesEmployee = async function(SalesEmployee: SalesEmployee): Promise<number>{
    try {
        const response = await axios.post('http://localhost:8080/api/employees/sales/', SalesEmployee)

        return response.data
    } catch (e) {
        throw new Error('Could not create sales employee')
    }

}

module.exports.updateSalesEmployee = async function( id: number, body: SalesEmployee ): Promise<void>{
    try {
        await axios.put('http://localhost:8080/api/employees/sales/' + id, body)
    } catch (e) {
        console.error(e)
        throw new Error('Could not update sales employee')
    }

}

module.exports.deleteSalesEmployee = async function( id: number ): Promise<void>{
    try {
        const response = await axios.delete('http://localhost:8080/api/employees/sales/' + id)

    } catch (e) {
        throw new Error('Could not delete sales employee')
    }

}