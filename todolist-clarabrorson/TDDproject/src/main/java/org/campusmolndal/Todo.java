package org.campusmolndal;

import org.bson.Document;

public class Todo {

    String _id;
    String text;
    boolean done;

    public Todo(String _id,String text, boolean done) {
        this._id = _id;
        this.text = text;
        this.done = done;
    }

    // Metod för att skapa ett Todo-objekt från ett Document-objekt
    public static Todo fromDoc(Document doc){

        String _id=doc.getString("_id");
        String text=doc.getString("text");
        boolean done=doc.getBoolean("done");

        return new Todo (_id, text, done);
    }

    // Metod för att skapa ett Document-objekt från ett Todo-objekt
    public Document toDoc() {
        Document doc = new Document();
        doc.append("_id", _id);
        doc.append("text", text);
        doc.append("done", done);
        return doc;
    }

    public String get_id() {
    if (_id == null) {
        return null;
    }
    return _id;
    }

    public String getText() {

        return text;
    }

    public boolean isDone()
    {
        return done;
    }

    public String toString() {
        return text + ", " + done + ", " + _id;
    }
}



