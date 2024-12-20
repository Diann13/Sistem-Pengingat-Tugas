import java.util.*

data class Task(val description: String, val deadline: String, var isCompleted: Boolean = false)

class TaskManager {
    private val tasks = mutableListOf<Task>()

    fun addTask(description: String, deadline: String) {
        tasks.add(Task(description, deadline))
        println("Tugas berhasil ditambahkan.")
    }

    fun viewTasks() {
        if (tasks.isEmpty()) {
            println("Tidak ada tugas yang tersedia.")
        } else {
            println("Daftar Tugas:")
            tasks.forEachIndexed { index, task ->
                val status = if (task.isCompleted) "Selesai" else "Belum Selesai"
                println("${index + 1}. ${task.description} (Deadline: ${task.deadline}) - $status")
            }
        }
    }

    fun markTaskAsCompleted(taskIndex: Int) {
        if (taskIndex in 1..tasks.size) {
            tasks[taskIndex - 1].isCompleted = true
            println("Tugas telah ditandai sebagai selesai.")
        } else {
            println("Indeks tugas tidak valid.")
        }
    }

    fun deleteTask(taskIndex: Int) {
        if (taskIndex in 1..tasks.size) {
            tasks.removeAt(taskIndex - 1)
            println("Tugas berhasil dihapus.")
        } else {
            println("Indeks tugas tidak valid.")
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val taskManager = TaskManager()

    println("Selamat datang di Sistem Pengingat Tugas Harian!")

    while (true) {
        println("\nMenu:")
        println("1. Tambahkan Tugas Baru")
        println("2. Lihat Daftar Tugas")
        println("3. Tandai Tugas sebagai Selesai")
        println("4. Hapus Tugas")
        println("5. Keluar")
        print("Pilih opsi: ")

        when (scanner.nextInt()) {
            1 -> {
                scanner.nextLine() // Clear the newline
                print("Masukkan deskripsi tugas: ")
                val description = scanner.nextLine()
                var deadline: String
                while (true) {
                    print("Masukkan tanggal deadline (yyyy-mm-dd): ")
                    deadline = scanner.nextLine()
                    if (isValidDate(deadline)) break
                    else println("Format tanggal tidak valid. Silakan coba lagi.")
                }
                taskManager.addTask(description, deadline)
            }
            2 -> taskManager.viewTasks()
            3 -> {
                print("Masukkan nomor tugas yang akan ditandai selesai: ")
                val taskIndex = scanner.nextInt()
                taskManager.markTaskAsCompleted(taskIndex)
            }
            4 -> {
                print("Masukkan nomor tugas yang akan dihapus: ")
                val taskIndex = scanner.nextInt()
                taskManager.deleteTask(taskIndex)
            }
            5 -> {
                println("Terima kasih telah menggunakan aplikasi ini.")
                break
            }
            else -> println("Opsi tidak valid. Silakan coba lagi.")
        }
    }
}

fun isValidDate(date: String): Boolean {
    return try {
        val parts = date.split("-")
        if (parts.size == 3) {
            val year = parts[0].toInt()
            val month = parts[1].toInt()
            val day = parts[2].toInt()
            year > 0 && month in 1..12 && day in 1..31
        } else {
            false
        }
    } catch (e: Exception) {
        false
    }
}
