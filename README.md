
1. app build.gradle:

```java
dependencies {
   implementation 'com.github.qurash98:AndroidSDK:$last version' 
}
```

2. Встройте в приложение вызов на оплату:
```java
@Override public void onClick(View v) {
   Intent intent = new Intent(YourActivity.this, PaymentActivity.class);
   intent.putExtra(EXTRA_ID, xAuth); //merchant id
   final Double sum = Double.valueOf(activityTestSum.getText().toString());
   intent.putExtra(EXTRA_AMOUNT, sum); // summ
   intent.putExtra(EXTRA_SAVE, activityTestMultiple.isChecked()); //Сохранить для многократной оплаты?
   intent.putExtra(EXTRA_LANG, "RU"); //lang "UZ" "RU"
   PaycomSandBox.setEnabled(true); 
   startActivityForResult(intent, 0);
}
```