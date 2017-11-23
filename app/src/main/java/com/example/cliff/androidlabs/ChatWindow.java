package com.example.cliff.androidlabs;


        import android.app.Activity;
        import android.content.Context;
        import android.content.res.Resources;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;



        import java.util.ArrayList;

public class ChatWindow extends Activity {
    protected static final String ACTIVITY_NAME = "ChatWindow";

    private ChatDatabaseHelper databaseHelper;
    private ChatAdapter messageAdapter;

    final ArrayList<String> chatArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        Resources resources = getResources();
        ListView listViewChat;
        listViewChat = (ListView) findViewById(R.id.listView);
        messageAdapter = new ChatAdapter(this);
        listViewChat.setAdapter(messageAdapter);
        final EditText editTextChat = (EditText) findViewById(R.id.chatview);
        Button buttonSend = (Button) findViewById(R.id.send_button);
        databaseHelper = new ChatDatabaseHelper(this);

        databaseHelper.openDatabase();
        readDatabase();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatString = editTextChat.getText().toString();
                databaseHelper.insert(chatString);
                readDatabase();
                editTextChat.setText("");
            }
        });
    }

    private void readDatabase(){
        chatArray.clear();
        Cursor cursor = databaseHelper.getRecords();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            chatArray.add(cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_CONTENT)));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString( cursor.getColumnIndex( ChatDatabaseHelper.COLUMN_CONTENT) ) );
        }
        messageAdapter.notifyDataSetChanged();
        Log.i(ACTIVITY_NAME, "Cursor’s  column count = " + cursor.getColumnCount() );
        for (int i = 0; i < cursor.getColumnCount(); i++){
            Log.i(ACTIVITY_NAME, "Cursor’s  column name = " + (i + 1) + ". " + cursor.getColumnName(i) );
        }
    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {

            super(ctx, 0);
        }

        public int getCount() {

            return chatArray.size();
        }

        public String getItem(int position) {
            return chatArray.get(position);
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position%2 == 0) {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
                TextView message = (TextView) result.findViewById((R.id.messageText2));
                message.setText(getItem(position));
            } else {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
                TextView message = (TextView) result.findViewById((R.id.messageText));
                message.setText(getItem(position));
            }
            return result;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.closeDatabase();
    }
}