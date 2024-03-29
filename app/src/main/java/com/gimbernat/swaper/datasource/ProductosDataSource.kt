package com.albertleal.gibernat.myapplication.datasources

import com.gimbernat.swaper.datasource.interfaces.IProductosDataSource
import com.gimbernat.swaper.models.Producto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ProductosDataSource(private val database: FirebaseDatabase) : IProductosDataSource {

    private var productos: List<Producto> = emptyList()

    fun subscribe(callback: (List<Producto>) -> Unit) {
        val ref = database.getReference("productos")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val fetchedProductos = mutableListOf<Producto>()
                for (productoSnapshot in snapshot.children) {

                    val productoId = productoSnapshot.key as String
                    val nombre = productoSnapshot.child("nombre").getValue(String::class.java)
                    val descripcion = productoSnapshot.child("descripcion").getValue(String::class.java)
                    val imageUrl = productoSnapshot.child("imagenes").getValue(String::class.java)

                    if (nombre != null && descripcion != null && imageUrl != null) {
                        val producto = Producto(nombre = nombre, descripcion = descripcion, imagenes = imageUrl, id = productoId)
                        fetchedProductos.add(producto)
                    }
                }

                // Actualizar copia local
                productos = fetchedProductos
                callback(fetchedProductos)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(emptyList()) // o llamar al callback con algún valor predeterminado
            }
        })
    }
     suspend fun addProduct(producto: Producto) {
        suspendCoroutine<Unit> { continuation ->
            val ref = database.getReference("productos")
            val newProductRef = ref.push()
            newProductRef.setValue(producto) { error, _ ->
                if (error == null) {
                    continuation.resume(Unit)
                } else {
                    continuation.resumeWithException(error.toException())
                }
            }
        }
    }

    suspend fun deleteProduct(productId: String) {
        suspendCoroutine<Unit> { continuation ->
            val ref = database.getReference("productos").child(productId)
            ref.removeValue { error, _ ->
                if (error == null) {
                    continuation.resume(Unit)
                } else {
                    continuation.resumeWithException(error.toException())
                }
            }
        }
    }

    suspend fun editProduct(productId: String, updatedProduct: Producto) {
        suspendCoroutine<Unit> { continuation ->
            val ref = database.getReference("productos").child(productId)
            ref.setValue(updatedProduct) { error, _ ->
                if (error == null) {
                    continuation.resume(Unit)
                } else {
                    continuation.resumeWithException(error.toException())
                }
            }
        }
    }

    override suspend fun fetch(): List<Producto> {
        return suspendCoroutine { continuation ->

            val ref = database.getReference("productos")
            ref.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val fetchedProductos = mutableListOf<Producto>()

                    for (productoSnapshot in snapshot.children) {
                        val producto = productoSnapshot.getValue(Producto::class.java)
                        if (producto != null) {
                            producto.id = productoSnapshot.key
                            fetchedProductos.add(producto)
                        }
                    }

                    // Actualizar copia local
                    productos = fetchedProductos

                    continuation.resume(fetchedProductos)
                }
                override fun onCancelled(error: DatabaseError) {
                    continuation.resumeWithException(error.toException())
                }
            })
        }
    }
    override fun get(id: String): Producto? {
        return productos.find { it.id == id }
    }
}
