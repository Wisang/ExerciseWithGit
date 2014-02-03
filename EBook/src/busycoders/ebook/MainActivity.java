package busycoders.ebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView;

public class MainActivity extends Activity implements
		AdapterView.OnItemClickListener {
	private TextView selection;
	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
			"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
			"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
			"augue", "purus" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		selection = (TextView) findViewById(R.id.selection);

		GridView grid = (GridView) findViewById(R.id.grid);
		grid.setAdapter(new ArrayAdapter<String>(this,
							R.layout.cell,
							items));
		grid.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		selection.setText(items[position]);
	}

}
