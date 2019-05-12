package id.flabiraya.mytodolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.flabiraya.mytodolist.data.TodoListDatabase

class MainActivity : AppCompatActivity(), TodoAdapter.OnTodoItemClickedListener {
    lateinit var mDatabase: DatabaseReference
    var toDoItemList: MutableList<ToDoItem>? = null
    private var adapter: TodoAdapter? = null
    private var todo_rv: RecyclerView? = null
    private var todoDatabase: TodoListDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        todo_rv = findViewById<View>(R.id.todo_rv) as RecyclerView

        //Adding click listener for FAB
        fab.setOnClickListener { view ->
            //Show Dialog here to add new Item
            addNewItemDialog()
        }

        mDatabase = FirebaseDatabase.getInstance().reference
        toDoItemList = mutableListOf<ToDoItem>()
        adapter = TodoAdapter()
        todoDatabase = TodoListDatabase.getInstance(this)
        adapter?.setTodoItemClickedListener(this)
        adapter?.todoList = toDoItemList
        todo_rv!!.adapter = adapter
        todo_rv!!.layoutManager = LinearLayoutManager(this)
        todo_rv!!.hasFixedSize()
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)
        mDatabase.orderByKey().addChildEventListener(childEventListener)
    }

    override fun onResume() {
        super.onResume()
        // check the Internet connection
        InternetCheck(object : InternetCheck.Consumer {
            override fun accept(internet: Boolean?) {
                if (internet == false) {
                    adapter?.todoList = todoDatabase?.getTodoDao()?.getTodoList() as MutableList<ToDoItem>?
                    adapter!!.notifyDataSetChanged()
                }
            }
        })

    }

    /**
     * This method will show show all data from Firebase DataSnapshot
     */
    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()
        //Check if current database contains any collection
        if (items.hasNext()) {
            val toDoListindex = items.next()
            val itemsIterator = toDoListindex.children.iterator()

            //check if the collection has any to do items or not
            while (itemsIterator.hasNext()) {
                //get current item
                val currentItem = itemsIterator.next()
                val todoItem = ToDoItem.create()
                //get current data in a map

                if (currentItem.getValue() is HashMap<*, *>) {
                    val map = currentItem.getValue() as HashMap<*, *>
                    //key will return Firebase ID
                    todoItem.objectId = currentItem.key
                    todoItem.itemText = map.get("itemText") as String?
                    toDoItemList!!.add(todoItem)
                }
            }
        }
        //alert adapter that has changed
        adapter!!.notifyDataSetChanged()
    }

    /**
     * This method will show a dialog bix where user can enter new item
     * to be added
     */
    private fun addNewItemDialog() {
        val alert = AlertDialog.Builder(this)
        val itemEditText = EditText(this)
        alert.setTitle("Add New Item")
        alert.setView(itemEditText)
        alert.setPositiveButton("Submit") { dialog, which ->
            val todoItem = ToDoItem.create()
            todoItem.itemText = itemEditText.text.toString()
            //We first make a push so that a new item is made with a unique ID
            val newItem = mDatabase.child(Constants.FIREBASE_ITEM).push()
            //then, we used the reference to set the value on that ID
            newItem.setValue(todoItem)

            // save item to database also
            todoDatabase!!.getTodoDao().saveTodo(todoItem)

            dialog.dismiss()
            toDoItemList!!.add(todoItem)
            adapter!!.notifyDataSetChanged()
            Toast.makeText(this, "Item saved with ID " + todoItem.objectId, Toast.LENGTH_SHORT).show()
        }
        alert.show()
    }


    /**
     * This method will show a dialog bix where user edit an exist item
     */
    private fun editItemDialog(todoItem: ToDoItem) {
        val alert = AlertDialog.Builder(this)
        val itemEditText = EditText(this)
        alert.setTitle("Edit Item")
        itemEditText.setText(todoItem.itemText)
        itemEditText.setSelection(todoItem.itemText!!.length)

        alert.setView(itemEditText)
        alert.setPositiveButton("Submit") { dialog, which ->
            val itemReference = mDatabase.child(Constants.FIREBASE_ITEM).child(todoItem.objectId)
            itemReference.child("itemText").setValue(itemEditText.text.toString())

//            update item in database also
            todoDatabase!!.getTodoDao().updateTodo(todoItem)
            dialog.dismiss()
            for (item in toDoItemList!!) {
                if (item.objectId.equals(todoItem.objectId)) {
                    item.itemText = itemEditText.text.toString();
                }
            }
            adapter!!.notifyDataSetChanged()
            Toast.makeText(this, "Item saved with ID " + todoItem.objectId, Toast.LENGTH_SHORT).show()
        }
        alert.show()
    }

    /**
     * invoke when data changes from Firebase console
     * */
    var childEventListener: ChildEventListener = object : ChildEventListener {
        override fun onCancelled(p0: DatabaseError?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
            addDataToList(dataSnapshot)
        }

        override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
        }
    }


    /**
     * init data from Firebase database
     */
    var itemListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            addDataToList(dataSnapshot)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }

    /**
     * show a dialog when user click on an item
     */
    override fun onTodoItemClicked(todo: ToDoItem) {
        val alertDialog = AlertDialog.Builder(this)
            .setItems(R.array.dialog_list, { dialog, which ->
                if (which == 0) {
                    editItemDialog(todo)
                } else {
                    //get child reference in database via the ObjectID
                    val itemReference = mDatabase.child(Constants.FIREBASE_ITEM).child(todo.objectId)
                    //deletion can be done via removeValue() method
                    itemReference.removeValue()

                    // remove item out of database also
                    todoDatabase?.getTodoDao()?.removeTodo(todo)

                    // remove item out of list
                    toDoItemList!!.remove(todo)
                    adapter!!.notifyDataSetChanged()
                }
                dialog.dismiss()
            })
            .create()
        alertDialog.show()
    }
}
