import { Client } from '../model/sl-client';

const axios = require('axios');

module.exports.getClientsReport = async function(token:string): Promise<Client> {
    try{
        const response = await axios.get('http://localhost:8080/api/clients/reports', {params: {token: token}})

        return response.data
    } catch (e) {
        throw new Error('Could not get clients reports')
    }
}


module.exports.getClientHighestValue = async function(token: string): Promise<Client> {
    try{
        const response = await axios.get('http://localhost:8080/api/clients/projects/top', {params: {token: token}})

        return response.data
    } catch (e) {
        throw new Error('Could not get client with highest value')
    }
}


module.exports.getClients = async function(token: string): Promise<Client> {
    try{
        const response = await axios.get('http://localhost:8080/api/clients', {params: {token: token}})

        return response.data
    } catch (e) {
        throw new Error('Could not get clients')
    }
}