import { Project } from '../model/sl-project';

const axios = require('axios');

module.exports.getProjectReport = async function(): Promise<Project> {
    try{
        const response = await axios.get('http://localhost:8080/api/projects/reports')

        return response.data
    } catch (e) {
        throw new Error('Could not get projects reports')
    }
}


module.exports.getProjectById = async function( id: number ): Promise<Project> {
    try{
        const response = await axios.get('http://localhost:8080/api/projects/' + id)

        return response.data
    } catch (e) {
        throw new Error('Could not get project')
    }
}


module.exports.setProjectAsCompleted = async function (id : number) {
    try{
        await axios.put(`http://localhost:8080/api/project/${id}`);
    } catch (e){
        throw new Error('Could not update project')
    }
}

module.exports.assignClientToProject = async function (id: number, project: Project) {
    try{
        await axios.put(`http://localhost:8080/api/project/${id}/clients`, project);
    } catch (e){
        throw new Error('Could not update project')
    }
}