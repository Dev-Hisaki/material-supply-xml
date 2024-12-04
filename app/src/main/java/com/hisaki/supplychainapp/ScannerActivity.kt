package com.hisaki.supplychainapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator


class ScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scanner)

        // Memulai scanner
        val integrator = IntentIntegrator(this@ScannerActivity)

        // Atur Activity untuk scanner menjadi CustomCaptureActivity
        integrator.setCaptureActivity(ScannerActivity::class.java)

        // Konfigurasi tambahan
        integrator.setOrientationLocked(true) // Mengunci orientasi
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Scan a barcode")
        integrator.initiateScan() // Memulai scanner
    }

    // Menangkap hasil scan
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                // Barcode berhasil discan
                val scannedData = result.contents
                // Tampilkan hasil
                println("Scanned Data: $scannedData")
            } else {
                // Jika tidak ada data
                println("No Data Found")
            }
        }
    }
}