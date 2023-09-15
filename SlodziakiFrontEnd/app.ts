import { Request, Response } from "express"
import { Product } from './model/product';
import { Order } from './model/order';
import { Login } from './model/auth';
import { Client } from './model/sl-client';
import { Project } from './model/sl-project';


import { DeliveryEmployees } from "./model/sl-delivery";

const express = require('express')
const app = express()
const path = require('path')
const nunjucks = require('nunjucks')
const session = require('express-session')


const appViews = path.join(__dirname, '/views/')

const nunjucksConfig = {
    autoescape: true,
    noCache: true,
    express: app
};

nunjucks.configure(appViews, nunjucksConfig)

app.set('view engine', 'html')

app.use('/public', express.static(path.join(__dirname, '/public')))

app.use(express.json())

app.use(express.urlencoded({ extended: true }))

app.use(session({ secret: 'NOT HARDCODED SECRET', cookie: { maxAge: 60000 }}));

declare module "express-session" {
    interface SessionData {
        token: string
        client: Client
        project: Project
        product: Product;
        order: Order;
        deliveryEmployee: DeliveryEmployees;
    }
}

app.listen(3000, () => {
    console.log('Server listening on port 3000')
})

app.get('/', async (req: Request, res: Response) => {
    res.render('pizza', { title: 'New Pizza Time!' })
})

//require('./controller/authController')(app);

<<<<<<< HEAD
//const authMiddleware = require('./middleware/auth')
//app.use(authMiddleware);
=======
// const authMiddleware = require('./middleware/auth')
// app.use(authMiddleware);
>>>>>>> ddd8618a30c16ef133fb80ba594ceeab5f8adc6e



require('./controller/productController')(app);

require('./controller/orderController')(app);

<<<<<<< HEAD
require('./controller/sl-clientController')(app);

require('./controller/sl-projectController')(app);
=======
require('./controller/sl-deliveryController')(app);
>>>>>>> ddd8618a30c16ef133fb80ba594ceeab5f8adc6e
