module main

import core
import extensions

function index = |arg| {
    println("TADA!!!!")
    
}


function main = |arg| {
    println(core.hello(""))
    println(extensions.hello(""))
}