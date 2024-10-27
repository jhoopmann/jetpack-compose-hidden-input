package de.jhoopmann.corepulse.watch.ui.component.input

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import java.math.BigDecimal
import java.math.BigInteger

@Composable
inline fun <reified T> HiddenInput(
    valueState: MutableState<T>,
    keyboardFocusRequester: FocusRequester,
    keyboardOptions: KeyboardOptions,
    crossinline validate: ((value: T?) -> Boolean) = { true }
) {
    val focusManager = LocalFocusManager.current
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val state: MutableState<String> = remember { mutableStateOf("") }

    TextField(modifier = Modifier
        .alpha(0f)
        .size(1.dp)
        .offset(x = displayMetrics.widthPixels.dp)
        .focusRequester(keyboardFocusRequester)
        .onFocusChanged { state.value = valueState.value?.toString() ?: "" },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onAny = {
            castValue<T>(state.value)?.let { casted ->
                if (validate(casted)) {
                    valueState.value = casted

                    focusManager.clearFocus(true)
                    defaultKeyboardAction(ImeAction.Done)
                } else {
                    state.value = valueState.value?.toString() ?: ""
                }
            }
        }),
        value = state.value,
        onValueChange = {
            state.value = (castValue<T>(it) ?: "").toString()
        })
}

inline fun <reified T> castValue(value: String): T? {
    return try {
        when (T::class) {
            Int::class -> value.toInt() as T
            UInt::class -> value.toUInt() as T
            Long::class -> value.toLong() as T
            ULong::class -> value.toULong() as T
            Double::class -> value.replace(',', '.').toDouble() as T
            Float::class -> value.replace(',', '.').toFloat() as T
            BigDecimal::class -> value.toBigDecimal() as T
            BigInteger::class -> value.toBigInteger() as T
            else -> value as T
        }
    } catch (e: Exception) {
        null
    }
}
