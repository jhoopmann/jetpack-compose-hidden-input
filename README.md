# Hidden Input Composable

Composable example to open keyboard from hidden textfield and cast values to specific MutableState<T> type

# Usage

Port input example

```
val focusRequester = remember { FocusRequester() }
val textState = remember { mutableStateOf(0u) } // UInt type

Chip(
  label = { Text(textState.value.toString()) },
  onClick = { focusRequester.requestFocus() }
)

HiddenInput(
  valueState = textState,
   keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Number,
      imeAction = ImeAction.Done,
      autoCorrectEnabled = false,
      showKeyboardOnFocus = true
  ),
  validate = { it in 1024u..49151u })
)    
```
