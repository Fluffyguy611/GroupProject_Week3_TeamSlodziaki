import { Application, Request, Response } from "express";
import { SalesEmployee } from '../model/slSalesEmployee';

const slSalesEmployeeService = require('../service/slSalesEmployeeService')

module.exports = function(app: Application) {

    app.get('/employees-sales', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await slSalesEmployeeService.getAllSalesEmployees()
        } catch (e) {
            console.error(e);
        }

        res.render('sl-list-all-sales-employees', {SalesEmployee: data})
    })

    app.get('/employees-sales/:id', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await slSalesEmployeeService.getSalesEmployeeById(req.params.id)
        } catch (e) {
            console.error(e);
        }

        res.render('sl-list-sales-employees-by-id', {SalesEmployee: data})
    })

    app.get('/add-sales-employee', async (req: Request, res: Response) => {
        res.render('sl-add-sales-employee')

    })

    app.post('/add-sales-employee', async (req: Request, res: Response) => {
        let data: SalesEmployee = req.body
        let id: Number

        try {
            id = await slSalesEmployeeService.createSalesEmployee(data)

            res.redirect('/employees-sales/' + id)
        } catch (e) {
            console.error(e)

            res.locals.errormessage = e.message

            res.render('sl-add-sales-employee', req.body)
        }

    })

    app.get('/update-sales-employee/:id', async (req: Request, res: Response) => {
       
        const employee = await slSalesEmployeeService.getSalesEmployeeById(req.params.id)
        res.render('sl-update-sales-employee', {employee})

    })

    app.post(('/sl-update-sales-employee'), async (req: Request, res: Response) => {
        let data: SalesEmployee = req.body
        let id: Number

        try {
            id = await slSalesEmployeeService.updateSalesEmployee(data)

            res.redirect('/employees-sales/' + id)
        } catch (e) {
            console.error(e)

            res.locals.errormessage = e.message

            res.render('sl-update-sales-employee')
        }
    })

    app.get(('/sl-delete-sales-employee/:id'), async (req: Request, res: Response) => {
        let id: string = req.params.id

        try {
            await slSalesEmployeeService.deleteSalesEmployee(id)

            res.redirect('/employees-sales/')
        } catch (e) {
            console.error(e)

            res.locals.errormessage = e.message

            res.render('sl-list-sales-employees-by-id')
        }
    })

}