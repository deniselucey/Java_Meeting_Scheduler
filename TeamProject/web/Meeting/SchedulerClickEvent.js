var maxMeetingsAtOnce = 4;
var tdSelected = new Array(maxMeetingsAtOnce + 1);
var counter = 0;

function TDClicked(date)
{
    document.getElementById("TEST").innerHTML =" Hello" + " World";
    document.getElementById("TEST").innerHTML = tdSelected;    
    var allReadySelected = false;
    for(i = 0; i < tdSelected.length; i++)
    {
        if(tdSelected[i] === date)
        {
            allReadySelected = true;
            tdSelected[i] = null;
            counter--;
        }
        else if(allReadySelected)
        {
            tdSelected[i-1] = tdSelected[i];
        }
    }
    if(!allReadySelected)
    {
        if(counter !== maxMeetingsAtOnce)
        {
            tdSelected[counter] = date;
            counter++;
        }
        else
        {
            window.alert("Max Number Of Meeting Set")
        }
    }
    document.getElementById("TEST").innerHTML = tdSelected;
}

function Commit()
{
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "ConfirmMeeting.jsp");

    var dateString = "";
    for(i = 0; i < counter;i++)
    {
        if(i !== 0)
        {
            dateString += ",";
        }
        dateString += tdSelected[i];
        
    }
    
    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "dates");
    hiddenField.setAttribute("value", dateString);

    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
    
}