# Hidden Input Composable

Composable example to open keyboard from hidden textfield and cast values to specific MutableState<T> type

# Usage

Port input example

```
HiddenInput<UInt>(keyboardFocusRequester = portFocusRequester,
    keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done,
        autoCorrectEnabled = false,
        showKeyboardOnFocus = true
    ),
    validate = { it in 1024u..49151u }) {
    value = it
}
```
