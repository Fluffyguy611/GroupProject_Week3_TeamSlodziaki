import { Project } from '../model/sl-project';

const axios = require('axios');

module.exports.getProjectReport = async function(token: string): Promise<Project> {
    try{
        const response = await axios.get('http://localhost:8080/api/projects/reports', {params: {token: token}})

        return response.data
    } catch (e) {
        throw new Error('Could not get projects reports')
    }
}


module.exports.getProjectById = async function( id: number , token:string): Promise<Project> {
    try{
        const response = await axios.get('http://localhost:8080/api/projects/' + id, {params: {token: token}})

        return response.data
    } catch (e) {
        throw new Error('Could not get project')
    }
}


module.exports.setProjectAsCompleted = async function (id : number, token:string) {
    try{
        await axios.put(`http://localhost:8080/api/project/${id}`, {params: {token: token}});
    } catch (e){
        throw new Error('Could not update project')
    }
}

module.exports.assignClientToProject = async function (id: number, project: Project, token: string) {
    try{
        await axios.put(`http://localhost:8080/api/project/${id}/clients`, project, {params: {token: token}});
    } catch (e){
        throw new Error('Could not update project')
    }
}