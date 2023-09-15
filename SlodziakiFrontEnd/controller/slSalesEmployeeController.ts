import { Application, Request, Response } from "express";
import { SalesEmployee } from '../model/slSalesEmployee';

const slSalesEmployeeService = require('../service/slSalesEmployeeService')

module.exports = function (app: Application) {

    app.get('/employees/sales', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await slSalesEmployeeService.getAllSalesEmployees()
        } catch (e) {
            console.error(e);
        }

        res.render('sl-list-all-sales-employees', { SalesEmployee: data })
    });

    app.get('/employees/sales/:id', async (req: Request, res: Response) => {
        let data = [];

        try {
            data = await slSalesEmployeeService.getSalesEmployeeById(req.params.id)
        } catch (e) {
            console.error(e);
        }

        res.render('sl-list-sales-employees-by-id', { SalesEmployee: data })
    });

    app.get('/add/sales/employee', async (req: Request, res: Response) => {
        res.render('sl-add-sales-employee')

    });

    app.post('/add/sales/employee', async (req: Request, res: Response) => {
        let data: SalesEmployee = req.body
        let id: Number

        try {
            id = await slSalesEmployeeService.createSalesEmployee(data)

            res.redirect('/employees/sales/' + id)
        } catch (e) {
            console.error(e)

            res.locals.errormessage = e.message

            res.render('sl-add-sales-employee', req.body)
        }

    });

    app.get('/add/sales/employee/base', async (req: Request, res: Response) => {
        if (!req.session.salesEmployee) {
            req.session.salesEmployee = {}
        }

        res.render('sl-add-sales-employee-base')
    });

    app.post('/add/sales/employee/base', async (req: Request, res: Response) => {
        req.session.salesEmployee["name"] = req.body.name
        req.session.salesEmployee["salary"] = req.body.salary

        res.redirect('/add/sales/employee/description')
    });

    app.get('/add/sales/employee/description', async (req: Request, res: Response) => {
        res.render('sl-add-sales-employee-description')
    });

    app.post('/add/sales/employee/description', async (req: Request, res: Response) => {
        req.session.salesEmployee["bankAccount"] = req.body.bankAccountNumber
        req.session.salesEmployee["insuranceNumber"] = req.body.nationalInsuranceNumber
        req.session.salesEmployee["commissionRate"] = req.body.commissionRate

    });

    app.get('/update/sales/employee/:id', async (req: Request, res: Response) => {

        const employee = await slSalesEmployeeService.getSalesEmployeeById(req.params.id);
        res.render('sl-update-sales-employee', { employee });

    });

    app.post('/update/sales/employee/:id', async (req: Request, res: Response) => {
        let data: SalesEmployee = req.body;
        let id: string = req.params.id;

        try {
            await slSalesEmployeeService.updateSalesEmployee(id, data);

            res.redirect('/employees-sales/' + id)
        } catch (e) {
            console.error(e)

            res.locals.errormessage = e.message

            res.render('sl-update-sales-employee')
        }
    });

    app.get('/sl/delete/sales/employee/:id', async (req: Request, res: Response) => {
        let id: string = req.params.id

        try {
            await slSalesEmployeeService.deleteSalesEmployee(id)

            res.redirect('/employees/sales/')
        } catch (e) {
            console.error(e)

            res.locals.errormessage = e.message

            res.render('sl-list-sales-employees-by-id')
        }
    });

}
