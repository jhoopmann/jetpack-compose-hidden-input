# Hidden Input Composable

Composable to open keyboard from hidden textfield and cast values to specific type

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
  validate = { it.takeIf { it in 1024u..49151u } ?: 1024u }
)    
```
