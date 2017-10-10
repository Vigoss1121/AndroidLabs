package com.example.cliff.androidlabs;


        import android.app.Activity;
        import android.content.Context;
        import android.content.res.Resources;
        import android.os.Bundle;
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

    final ArrayList<String> chatArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        Resources resources = getResources();
        ListView listViewChat;
        listViewChat = (ListView) findViewById(R.id.listView);
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listViewChat.setAdapter(messageAdapter);
        final EditText editTextChat = (EditText) findViewById(R.id.chatview);
        Button buttonSend = (Button) findViewById(R.id.send_button);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatString = editTextChat.getText().toString();
                chatArray.add(chatString);
                messageAdapter.notifyDataSetChanged();
                editTextChat.setText("");
            }
        });
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
}