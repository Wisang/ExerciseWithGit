package busycoders.ebook;

import java.util.Date;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NowStyled extends Activity implements View.OnClickListener{
	Button btn;
	
	@Override
	  public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    
	    setContentView(R.layout.);

	    btn=(Button)findViewById(R.id.bu);
	    btn.setOnClickListener(this);
	    updateTime();
	  }

	  @Override
	  public void onClick(View view) {
	    updateTime();
	  }

	  private void updateTime() {
	    btn.setText(new Date().toString());
	  }

}
