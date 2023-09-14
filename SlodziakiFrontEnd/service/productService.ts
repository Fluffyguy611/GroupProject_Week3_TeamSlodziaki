import { Product } from '../model/product';

const axios = require('axios');
const productValidator = require('../validator/productValidator')

module.exports.getProducts = async function(): Promise<Product[]>{
    try{
        const response = await axios.get('http://localhost:8080/api/products')

        return response.data
    } catch (e) {
        throw new Error('Could not get products')
    }
}


module.exports.getProductsById = async function( id: number ): Promise<Product> {
    try{
        const response = await axios.get('http://localhost:8080/api/products/' + id)

        return response.data
    } catch (e) {
        throw new Error('Could not get product')
    }
}

module.exports.createProduct = async function (product: Product, token: string): Promise<number> {
   try{
        const response = await axios.post('http://localhost:8080/api/products/', product, { params: {token: token}})

        return response.data
    } catch (e){
        throw new Error('Could not create product')
    }
}