package com.example.tclremote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF0B1018)
private val Panel = Color(0xFF1C2A3A)
private val Accent = Color(0xFF7DE4B0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { RemoteUI() } }
}

@Composable fun RemoteUI() {
    var tab by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("Pronto para iniciar descoberta da TV") }
    MaterialTheme(colorScheme = darkColorScheme(primary=Accent, background=Navy, surface=Panel)) {
        Surface(Modifier.fillMaxSize(), color=Navy) {
            Column(Modifier.fillMaxSize().padding(18.dp), verticalArrangement=Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment=Alignment.CenterVertically, horizontalArrangement=Arrangement.SpaceBetween) {
                    Column { Text("SMART CONTROL", color=Accent, fontSize=11.sp); Text("TCL Remote", fontSize=26.sp) }
                    Text("● LOCAL", color=Accent)
                }
                Card(colors=CardDefaults.cardColors(containerColor=Panel)) { Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment=Alignment.CenterVertically, horizontalArrangement=Arrangement.SpaceBetween) {
                    Column { Text("TCL Google TV"); Text("Pareamento e conexão: em desenvolvimento", fontSize=11.sp, color=Color.LightGray) }
                    Button(onClick={message="Descoberta de dispositivos ainda não implementada nesta build."}) { Text("Buscar TV") }
                } }
                when(tab) {
                    0 -> ControlPanel { message="Comando demonstrativo: $it" }
                    1 -> Column { Card(colors=CardDefaults.cardColors(containerColor=Panel)) { Column(Modifier.fillMaxWidth().padding(20.dp), horizontalAlignment=Alignment.CenterHorizontally) { Text("Touchpad",fontSize=22.sp); Spacer(Modifier.height(16.dp)); Text("Área gestual planejada",color=Color.LightGray); Spacer(Modifier.height(40.dp)); Button(onClick={message="OK (demo)"}) {Text("Selecionar") } } } }
                    2 -> Column(verticalArrangement=Arrangement.spacedBy(8.dp)) { Text("Aplicativos",fontSize=22.sp); listOf("YouTube","Netflix","Globoplay","Prime Video","Disney+","Spotify").chunked(2).forEach { row -> Row(horizontalArrangement=Arrangement.spacedBy(8.dp)) { row.forEach { app -> Button(onClick={message="Atalho $app: ainda não ligado ao protocolo"},modifier=Modifier.weight(1)) {Text(app)} } } } } }
                    else -> Column { Text("Configurações",fontSize=22.sp); Text("Descoberta mDNS, pareamento TLS, armazenamento seguro e feedback tátil serão implementados.",color=Color.LightGray) }
                }
                Card(colors=CardDefaults.cardColors(containerColor=Panel)) { Text(message,Modifier.padding(12.dp),fontSize=12.sp,color=Color.LightGray) }
                Spacer(Modifier.weight(1f))
                NavigationBar(containerColor=Panel) { listOf("Controle","Touch","Apps","Ajustes").forEachIndexed { i, label -> NavigationBarItem(selected=tab==i,onClick={tab=i},icon={Text(listOf("⌖","✥","▦","⚙")[i])},label={Text(label)}) } }
            }
        }
    }
}

@Composable fun ControlPanel(onCmd:(String)->Unit) {
    Column(horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.spacedBy(10.dp),modifier=Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){Button(onClick={onCmd("POWER")},modifier=Modifier.weight(1)){Text("⏻ Energia")};Button(onClick={onCmd("MUTE")},modifier=Modifier.weight(1)){Text("Mudo")}}
        Card(shape=CircleShape,colors=CardDefaults.cardColors(containerColor=Panel),modifier=Modifier.size(260.dp)) { Column(Modifier.fillMaxSize().padding(18.dp),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.SpaceEvenly) {
            Button(onClick={onCmd("UP")},shape=CircleShape){Text("▲")}
            Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween,verticalAlignment=Alignment.CenterVertically){Button(onClick={onCmd("LEFT")},shape=CircleShape){Text("◀")};Button(onClick={onCmd("OK")},shape=CircleShape,colors=ButtonDefaults.buttonColors(containerColor=Accent,contentColor=Navy)){Text("OK")};Button(onClick={onCmd("RIGHT")},shape=CircleShape){Text("▶")}}
            Button(onClick={onCmd("DOWN")},shape=CircleShape){Text("▼")}
        } }
        Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(6.dp)){Button(onClick={onCmd("BACK")},modifier=Modifier.weight(1)){Text("Voltar")};Button(onClick={onCmd("HOME")},modifier=Modifier.weight(1)){Text("Início")};Button(onClick={onCmd("MENU")},modifier=Modifier.weight(1)){Text("Menu")}}
        Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){Button(onClick={onCmd("VOL_DOWN")},modifier=Modifier.weight(1)){Text("− Volume")};Button(onClick={onCmd("VOL_UP")},modifier=Modifier.weight(1)){Text("+ Volume")}}
    }
}
