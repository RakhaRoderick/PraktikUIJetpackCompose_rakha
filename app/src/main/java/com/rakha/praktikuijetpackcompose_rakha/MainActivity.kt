package com.rakha.praktikuijetpackcompose_rakha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Lan
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ============================================================================
// PALET WARNA BARU (Teal & Deep Slate Navy - High Contrast & Modern Academic)
// ============================================================================
private val NavyPrimary = Color(0xFF0F172A)
private val TealAccent = Color(0xFF0D9488)
private val TealContainer = Color(0xFFCCFBF1)
private val SlateCanvas = Color(0xFFF8FAFC)
private val TextDark = Color(0xFF0F172A)
private val TextMuted = Color(0xFF475569)

// Accent category tokens (Kontras tinggi & mudah dibaca)
private val SoftEmerald = Color(0xFFD1FAE5)
private val DarkEmerald = Color(0xFF047857)

private val SoftIndigo = Color(0xFFE0E7FF)
private val DarkIndigo = Color(0xFF4338CA)

private val SoftAmber = Color(0xFFFEF3C7)
private val DarkAmber = Color(0xFFB45309)

private val SoftRose = Color(0xFFFFE4E6)
private val DarkRose = Color(0xFFBE123C)

private val SoftCyan = Color(0xFFCFFAFE)
private val DarkCyan = Color(0xFF0E7490)

private val SoftViolet = Color(0xFFEDE9FE)
private val DarkViolet = Color(0xFF6D28D9)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KampusKuTheme {
                AdaptiveDashboard()
            }
        }
    }
}

@Composable
private fun KampusKuTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = TealAccent,
        onPrimary = Color.White,
        primaryContainer = TealContainer,
        onPrimaryContainer = NavyPrimary,
        secondary = NavyPrimary,
        background = SlateCanvas,
        surface = Color.White,
        onSurface = TextDark,
        onSurfaceVariant = TextMuted,
        tertiary = DarkEmerald,
    )
    MaterialTheme(colorScheme = colors, content = content)
}

/**
 * Single page dashboard yang responsif dan merespons lebar layar yang tersedia.
 * Compact < 600 dp, Medium 600-839 dp, Expanded >= 840 dp.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdaptiveDashboard() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "KampusKu",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Dashboard Perkuliahan & Akademik",
                            style = MaterialTheme.typography.labelMedium,
                            color = TextMuted
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = "Logo KampusKu",
                        tint = TealAccent,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(28.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = TextDark
                )
            )
        },
        containerColor = SlateCanvas
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val currentWidth = maxWidth
            val widthClass = when {
                currentWidth < 600.dp -> "COMPACT · PONSEL"
                currentWidth < 840.dp -> "MEDIUM · LAYAR SEDANG"
                currentWidth < 1200.dp -> "EXPANDED · TABLET/LAPTOP"
                else -> "LARGE · LAYAR BESAR"
            }
            val showSideRail = currentWidth >= 840.dp

            Row(modifier = Modifier.fillMaxSize()) {
                if (showSideRail) {
                    DashboardSideRail()
                }
                DashboardContent(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    widthClassLabel = widthClass
                )
            }
        }
    }
}

/** Navigasi samping untuk layar lebar (Expanded) */
@Composable
private fun DashboardSideRail() {
    Column(
        modifier = Modifier
            .width(88.dp)
            .fillMaxHeight()
            .background(Color.White)
            .padding(top = 24.dp, start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        SideRailItem(Icons.Filled.Dashboard, "Beranda", selected = true)
        SideRailItem(Icons.Filled.CalendarMonth, "Jadwal", selected = false)
        SideRailItem(Icons.AutoMirrored.Filled.MenuBook, "Materi", selected = false)
        SideRailItem(Icons.Filled.BarChart, "Nilai", selected = false)
    }
}

@Composable
private fun SideRailItem(icon: ImageVector, label: String, selected: Boolean) {
    val tileColor = if (selected) TealContainer else Color.Transparent
    val iconColor = if (selected) TealAccent else TextMuted
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            color = tileColor,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.size(width = 56.dp, height = 40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = label, tint = iconColor)
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall, color = iconColor)
    }
}

@Composable
private fun DashboardContent(modifier: Modifier, widthClassLabel: String) {
    val metrics = listOf(
        MetricData(
            label = "Mata Kuliah",
            value = "7",
            detail = "Semester aktif berjalan",
            icon = Icons.AutoMirrored.Filled.MenuBook,
            accent = DarkIndigo,
            container = SoftIndigo
        ),
        MetricData(
            label = "Tugas Selesai",
            value = "16 / 18",
            detail = "88% tugas telah dikumpulkan",
            icon = Icons.Filled.AssignmentTurnedIn,
            accent = DarkEmerald,
            container = SoftEmerald
        ),
        MetricData(
            label = "Jadwal Hari Ini",
            value = "1",
            detail = "Kelas perkuliahan aktif",
            icon = Icons.Filled.Schedule,
            accent = DarkCyan,
            container = SoftCyan
        )
    )

    // Minimal 6 Mata Kuliah (Memenuhi syarat tugas: Tambahkan minimal 2 kartu mata kuliah)
    val courses = listOf(
        CourseData(
            title = "Pemrograman Mobile",
            subtitle = "Pengembangan aplikasi modern dengan Jetpack Compose dan Kotlin",
            note = "Pertemuan 5 · Layout Adaptif & Design System",
            progress = 0.75f,
            icon = Icons.Filled.School,
            accent = DarkIndigo,
            container = SoftIndigo
        ),
        CourseData(
            title = "Basis Data Lanjut",
            subtitle = "Normalisasi, indexing, dan optimasi query tingkat lanjut",
            note = "Tugas Praktikum 3 · Dikumpulkan Jumat",
            progress = 0.60f,
            icon = Icons.Filled.Science,
            accent = DarkEmerald,
            container = SoftEmerald
        ),
        CourseData(
            title = "Struktur Data & Algoritma",
            subtitle = "Penerapan Array, Linked List, Tree, Graph, dan Pencarian",
            note = "Latihan Mandiri & Kuis Minggu Depan",
            progress = 0.85f,
            icon = Icons.Filled.Code,
            accent = DarkAmber,
            container = SoftAmber
        ),
        CourseData(
            title = "Analisis & Visualisasi Data",
            subtitle = "Statistik deskriptif dan pengolahan dataset perkuliahan",
            note = "Materi Minggu ke-6 telah diunggah",
            progress = 0.45f,
            icon = Icons.Filled.BarChart,
            accent = DarkRose,
            container = SoftRose
        ),
        CourseData(
            title = "Kecerdasan Buatan",
            subtitle = "Konsep dasar Machine Learning, Neural Networks, dan Search Algorithm",
            note = "Proyek Kelompok · Diskusi Tahap 1",
            progress = 0.50f,
            icon = Icons.Filled.Psychology,
            accent = DarkViolet,
            container = SoftViolet
        ),
        CourseData(
            title = "Jaringan Komputer & Keamanan",
            subtitle = "Arsitektur OSI Layer, IP Subnetting, Routing, dan Keamanan Sistem",
            note = "Persiapan Ujian Tengah Semester",
            progress = 0.65f,
            icon = Icons.Filled.Lan,
            accent = DarkCyan,
            container = SoftCyan
        ),
        CourseData(
            title = "Desain Pengalaman Pengguna (UI/UX)",
            subtitle = "Riset pengguna, Wireframing, Design System, dan Prototyping Interaktif",
            note = "Review Portofolio & Feedback Pengguna",
            progress = 0.90f,
            icon = Icons.Filled.Palette,
            accent = TealAccent,
            container = TealContainer
        )
    )

    // Grid minSize disetel ke 260.dp (Memenuhi syarat tugas #3: minSize grid misal 220dp atau 260dp)
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 260.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            WindowClassBanner(widthClassLabel)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            WelcomeCard()
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            SectionHeader(
                title = "Ringkasan Akademik",
                subtitle = "Ringkasan performa perkuliahan berpindah otomatis sesuai lebar layar."
            )
        }
        items(metrics) { metric ->
            MetricCard(metric)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            SectionHeader(
                title = "Mata Kuliah Semester Ini",
                subtitle = "Grid menggunakan minSize 260 dp agar isi kartu tetap luas dan teks tidak terpotong."
            )
        }
        items(courses) { course ->
            CourseCard(course)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Data contoh untuk praktikum · Tampilan adaptif Jetpack Compose",
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
private fun WindowClassBanner(label: String) {
    Surface(
        color = TealContainer,
        contentColor = TextDark,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = TealAccent
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Mode Layout: $label",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun WelcomeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = NavyPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Selamat datang, Rakha!",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Atur kegiatan kuliahmu dengan lebih terarah. Tampilan ini menyesuaikan ruang layar yang tersedia secara otomatis.",
                    color = Color(0xFFCBD5E1),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Surface(
                shape = CircleShape,
                color = Color(0xFF1E293B),
                modifier = Modifier.size(58.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.School,
                        contentDescription = "Ilustrasi Pendidikan",
                        tint = TealAccent,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 2.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = TextMuted
        )
    }
}

private data class MetricData(
    val label: String,
    val value: String,
    val detail: String,
    val icon: ImageVector,
    val accent: Color,
    val container: Color
)

@Composable
private fun MetricCard(data: MetricData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = data.container,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = data.icon,
                            contentDescription = null,
                            tint = data.accent
                        )
                    }
                }
                Spacer(Modifier.width(10.dp))
                Text(
                    text = data.label,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = TextMuted
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = data.value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = data.detail,
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted
            )
        }
    }
}

private data class CourseData(
    val title: String,
    val subtitle: String,
    val note: String,
    val progress: Float,
    val icon: ImageVector,
    val accent: Color,
    val container: Color
)

/**
 * Kartu Mata Kuliah.
 * Memastikan semua teks panjang dapat terbaca sepenuhnya tanpa terpotong (Requirement #4).
 */
@Composable
private fun CourseCard(course: CourseData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Surface(
                    color = course.container,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = course.icon,
                            contentDescription = null,
                            tint = course.accent,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    // Teks judul & subjudul ditampilkan penuh tanpa terpotong
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = course.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Progres Belajar",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextMuted
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${(course.progress * 100).toInt()}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = course.accent,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { course.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(7.dp),
                color = course.accent,
                trackColor = course.container
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = course.note,
                style = MaterialTheme.typography.labelSmall,
                color = TextMuted
            )
        }
    }
}

@Preview(name = "Ponsel · Compact", showBackground = true, widthDp = 393, heightDp = 850)
@Composable
private fun PhonePreview() {
    KampusKuTheme { AdaptiveDashboard() }
}

@Preview(name = "Tablet · Expanded", showBackground = true, widthDp = 1000, heightDp = 800)
@Composable
private fun TabletPreview() {
    KampusKuTheme { AdaptiveDashboard() }
}
