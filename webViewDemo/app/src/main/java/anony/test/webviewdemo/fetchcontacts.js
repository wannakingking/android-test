window.onload=function()
{
    window.phonebook.debugout("inside js onload");
    var persons= window.phonebook.getContacts();

    if(persons)
    {
        window.phonebook.debugout(persons.length() + "of contacts entries are fetched");
        var contacts = document.getElementById("contacts");
        var i = 0;

        while(i<persons.length())
        {
            pnode = document.createElement("p");
            tnode = document.createTextNode("name:" + persons.get(i).getName()
                                           +"number:" + persons.get(i).getNumber());
            pnode.appendChild(tnode);
            contacts.appendChild(pnode);
            i++;
        }
    }
    else
    {
        window.phonebook.debugout("personsis undefined");
    }
}
