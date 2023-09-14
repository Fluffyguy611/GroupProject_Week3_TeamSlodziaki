import { Application, Request, Response } from "express";
import { Client } from "../model/sl-client";

const clientService = require('../service/sl-clientService')


module.exports = function(app: Application) {

    app.get('/clients/reports', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await clientService.getClientsReport(req.params.token)
        } catch (e){
            console.error(e)
        }

        res.render('sl-list-clients-reports', {clients: data})
    })

    app.get('/clients/projects/top', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await clientService.getClientHighestValue(req.params.token)
        } catch (e){
            console.error(e)
        }

        res.render('sl-view-client-top', {client: data})
    })
}