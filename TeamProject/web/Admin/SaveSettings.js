/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function test()
{
    document.getElementById("test").innerHTML = "key";
    
}
function save(key)
{
    //document.getElementById("test").innerHTML =key;
    
    var valueToSet = document.getElementById(key).value;
   
    
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "AdminHomePage.jsp");

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "key");
    hiddenField.setAttribute("value", key );

    var hiddenField1 = document.createElement("input");
    hiddenField1.setAttribute("type", "hidden");
    hiddenField1.setAttribute("name", "value");
    hiddenField1.setAttribute("value", valueToSet );
 
    form.appendChild(hiddenField);
    form.appendChild(hiddenField1);
    document.body.appendChild(form);
    form.submit();
    //document.getElementById("test").innerHTML = key + "  " + valueToSet;
    
    
    
}