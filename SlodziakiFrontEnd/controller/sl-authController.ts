import { Application, Request, Response } from "express"
import { Login } from "../model/auth";

const authService = require('../service/authService')