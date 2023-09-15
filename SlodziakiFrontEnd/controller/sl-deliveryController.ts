import { Application, Request, Response } from "express"
import { DeliveryEmployees } from "../model/sl-delivery"

const deliveryService = require('../service/sl-deliveryService')

module.exports=function(app: Application) {


    app.get('/employees/delivery/create', async (req: Request, rest: Response) => {
        rest.render('sl-add-delivery-employee')
    })


    app.post('/employees/delivery/create', async (req: Request, res: Response) => {
        let data: DeliveryEmployees = req.body
        let id: Number
          

        try {
            id = await deliveryService.createDeliveryEmployee(data)
            res.redirect('/employees/delivery/' + id)
        } catch (e) {
            console.error(e)
            res.locals.errormessage = e.message
            res.render('sl-add-delivery-employee', req.body)
        }
    
    })


// app.put('/employees/delivery/:id', async (req, res) => {
//     let id = req.params.id;
//     let data = req.body;

//     try {
//         await deliveryService.updateDeliveryEmployeeById(id, data);
//         res.redirect('/deliveryEmployee/' + id);
//     } catch (e) {
//         console.error(e);
//         res.locals.errormessage = e.message;
//         res.render('update-delivery-employee', { id, ...data }); 
//     }
// });

////////

app.get('/employees/delivery', async (req: Request, res: Response) => { //localhost3000
    let data = [];

    try {
        data = await deliveryService.getAllDeliveryEmployees() 
    } catch (e){
        console.error(e);
    }

    res.render('sl-list-delivery-employees', { DeliveryEmployee: data})
})


//////

app.get('/employees/delivery/:id', async (req: Request, res: Response) => {
    let data = [];

    try {
        data = await deliveryService.getDeliveryEmployeeById(req.params.id);
    } catch (e){
        console.error(e);
    }

    res.render('sl-view-delivery-employee', { DeliveryEmployee: data })
})

///


// app.delete('/employees/delivery/:id', async (req, res) => {
//     let id = req.params.id;

//     try {
//         await deliveryService.deleteDeliveryEmployee(id);
//         res.redirect('/deliveryEmployees'); 
//     } catch (e) {
//         console.error(e);
//         res.locals.errormessage = e.message;
//         res.render('error page'); 
//     }
// });




}


