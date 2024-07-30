package rodriguez.miguel.practica1_dialogos_segundo_parcial;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonSimpleDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDialog();
            }
        });

        findViewById(R.id.buttonCustomDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showSimpleDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirmación")
                .setMessage("¿Está usted seguro de lo que seleccionó?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Seleccionó Sí", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Seleccionó No", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showCustomDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);

        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextLastName = dialogView.findViewById(R.id.editTextLastName);
        final EditText editTextAge = dialogView.findViewById(R.id.editTextAge);
        final EditText editTextNationality = dialogView.findViewById(R.id.editTextNationality);
        final EditText editTextNumber = dialogView.findViewById(R.id.editTextNumber);

        final Spinner spinnerGender = dialogView.findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);

        final Spinner spinnerMaritalStatus = dialogView.findViewById(R.id.spinnerMaritalStatus);
        ArrayAdapter<CharSequence> adapterMaritalStatus = ArrayAdapter.createFromResource(this,
                R.array.marital_status_array, android.R.layout.simple_spinner_item);
        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaritalStatus.setAdapter(adapterMaritalStatus);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editTextName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String age = editTextAge.getText().toString();
                String gender = spinnerGender.getSelectedItem().toString();
                String maritalStatus = spinnerMaritalStatus.getSelectedItem().toString();
                String nationality = editTextNationality.getText().toString();
                String number = editTextNumber.getText().toString();

                Toast.makeText(MainActivity.this, "Registro listo: " + name + " " + lastName, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
}