package uz.anor.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import uz.anor.app.R;
import uz.anor.payment.PaymentActivity;
import uz.anor.payment.model.Result;
import uz.anor.payment.utils.PaycomSandBox;

import static uz.anor.payment.PaymentActivity.EXTRA_AMOUNT;
import static uz.anor.payment.PaymentActivity.EXTRA_ID;
import static uz.anor.payment.PaymentActivity.EXTRA_LANG;
import static uz.anor.payment.PaymentActivity.EXTRA_RESULT;
import static uz.anor.payment.PaymentActivity.EXTRA_SAVE;

public class TestActivity extends AppCompatActivity {

  private static final String TAG = "TestActivity";
  private static final String xAuth = "5c38330ae6a51ca84399f7e7";

  private EditText activityTestSum;
  private CheckBox activityTestMultiple;
  private Button activityTestPayment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);

    activityTestSum = (EditText) findViewById(R.id.activity_test_sum);
    activityTestMultiple = (CheckBox) findViewById(R.id.activity_test_multiple);
    activityTestPayment = (Button) findViewById(R.id.activity_test_payment);

    activityTestPayment.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(TestActivity.this, PaymentActivity.class);
        intent.putExtra(EXTRA_ID, xAuth);
        //Если чисел после запятой больше 2-ух, то они будут отброшены
        final Double sum = Double.valueOf(activityTestSum.getText().toString());
        intent.putExtra(EXTRA_AMOUNT, sum);
        intent.putExtra(EXTRA_SAVE, activityTestMultiple.isChecked());
        intent.putExtra(EXTRA_LANG, "RU");
        PaycomSandBox.setEnabled(true);
        startActivityForResult(intent, 0);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      Result result = data.getParcelableExtra(EXTRA_RESULT);
      Log.d(TAG, result.toString());
    } else if (resultCode == RESULT_CANCELED) {
        Log.d(TAG, "Payment canceled");
    }
  }
}