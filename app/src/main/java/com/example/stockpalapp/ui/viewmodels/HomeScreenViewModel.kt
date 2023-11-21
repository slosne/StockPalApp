package com.example.stockpalapp.ui.viewmodels


import androidx.lifecycle.ViewModel
import com.example.stockpalapp.data.repositories.AuthRepository
import com.example.stockpalapp.data.repositories.PantryRepository
import com.example.stockpalapp.data.repositories.RecipeRepository
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

    @HiltViewModel
    class HomeScreenViewModel @Inject constructor(
        recipeRepository: RecipeRepository,
        pantryRepository: PantryRepository,
        authRepository: AuthRepository
        ) : ViewModel() {

        val pantry = pantryRepository.pantry
        val pantryProducts = pantryRepository.pantryProduct
        val currentUser = authRepository.currentUser?.displayName

        val sortedProductsByExpDate = pantryProducts.map { pantryList ->
            pantryList.filter { it.expDate != null }
            pantryList.sortedBy { it.expDate?.seconds }
        }

        val recipes = recipeRepository.recipes

        //CHAT GPT help below to compare todays date with a converted Timestamp

        fun calculateDaysRemaining(expiryDate: Timestamp?): Int? {
            val currentDate = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            return expiryDate?.toDate()?.let { date ->
                Calendar.getInstance().apply {
                    timeInMillis = date.time

                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
            }?.let { expDate ->
                val daysDifference = TimeUnit.MILLISECONDS.toDays(expDate.timeInMillis - currentDate.timeInMillis)
                daysDifference.toInt()
            }
        }



    }