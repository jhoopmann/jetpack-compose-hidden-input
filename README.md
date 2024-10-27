# Hidden Input Composable

Composable example to open keyboard from hidden textfield and cast values to specific type

# Usage

```
val focusRequester = remember { FocusRequester() }
val textState = remember { mutableStateOf(0u) } // UInt type

Chip(
  label = { Text(textState.value.toString()) },
  onClick = { focusRequester.requestFocus() }
)

HiddenInput(
  valueState = textState,
  keyboardFocusRequester = focusRequester,
  keyboardType = KeyboardType.Number,
  validate = { it in 1024u..49151u })
)    
```
