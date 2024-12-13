package com.example.nearby.ui.screen.qrcode

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import com.example.nearby.MainActivity

import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


@Composable
fun QrCodeScannerScreen(modifier: Modifier = Modifier, onCompleteScan: (String) -> Unit) {
    val context = LocalContext.current

    val scanOptions = ScanOptions().setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        .setPrompt("Leia o QR Code do Cupom").setCameraId(0).setBeepEnabled(false)
        .setOrientationLocked(false).setBarcodeImageEnabled(true)

    val barCodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        onCompleteScan(result.contents.orEmpty())
    }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                barCodeLauncher.launch(scanOptions)
            } else {
                ActivityResultContracts.RequestPermission()
            }


        }

    fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            barCodeLauncher.launch(scanOptions)
            return
        } else if (shouldShowRequestPermissionRationale(
                context as MainActivity,
                Manifest.permission.CAMERA
            )
        ) {
            Toast.makeText(
                context,
                "Necessário permissão de câmera para continuar",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

    }

    LaunchedEffect(key1 = true) {
        checkCameraPermission()
    }

    Column(modifier = modifier.fillMaxSize()) {}

}