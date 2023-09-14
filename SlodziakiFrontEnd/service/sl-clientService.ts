import { Client } from '../model/sl-client';

const axios = require('axios');

module.exports.getClientsReport = async function(): Promise<Client> {
    try{
        const response = await axios.get('http://localhost:8080/api/clients/reports')

        return response.data
    } catch (e) {
        throw new Error('Could not get clients reports')
    }
}


module.exports.getClientHighestValue = async function(): Promise<Client> {
    try{
        const response = await axios.get('http://localhost:8080/api/clients/projects/top')

        return response.data
    } catch (e) {
        throw new Error('Could not get client with highest value')
    }
}


module.exports.getClients = async function(): Promise<Client> {
    try{
        const response = await axios.get('http://localhost:8080/api/clients')

        return response.data
    } catch (e) {
        throw new Error('Could not get clients')
    }
}