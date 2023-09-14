import { Application, Request, Response } from "express";
import { Project } from "../model/sl-project";
import { Client } from "../model/sl-client";

const projectService = require('../service/sl-projectService')
const clientService = require('../service/sl-clientService')


module.exports = function(app: Application) {

    app.get('/projects/reports', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await projectService.getProjectReport()
        } catch (e){
            console.error(e)
        }

        res.render('sl-list-projects-reports', {projects: data})
    })

    app.get('/projects/:id', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await projectService.getProjectById(req.params.id)
        } catch (e){
            console.error(e);
        }

        res.render('sl-view-project', { project: data })
    })

    app.get('/projects/:id/setCompleted', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await projectService.getProjectById(req.params.id)
        } catch (e){
            console.error(e);
        }

        res.render('sl-view-project-completed', { project: data })
    })


    app.post('/projects/:id/setCompleted', async (req: Request, res: Response) => {
        let data: Project = req.body
        try {
            await projectService.setProjectAsCompleted(req.params.id)

            res.redirect('/projects/reports')
        } catch (e){
            console.error(e);

            
            res.locals.errormessage = e.message

            res.render('sl-view-project-completed', { project: data })
        }
    })

    app.get('/projects/:project_id/clients', async (req: Request, res: Response) => {
        let project_data = [];
        let client_data = [];

        try {
            project_data = await projectService.getProjectById(req.params.project_id)
            client_data = await clientService.getClients()
        } catch (e){
            console.error(e);
        }

        res.render('sl-view-project-assign-client', { projects: project_data, clients: client_data })
    })

    app.post('/projects/:project_id/clients', async (req: Request, res: Response) => {
        let data: Project = req.body
        try {
            await projectService.assignClientToProject(req.params.project_id, data)

            res.redirect('/projects/reports')
        } catch (e){
            console.error(e);

            
            res.locals.errormessage = e.message

            res.render('sl-view-project-assign-client', { project: data })
        }
    })
}