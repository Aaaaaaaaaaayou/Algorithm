/**
 * Created by ayou on 17/9/17.
 */
function match(str1, str2) {
  if (str1 === '[' && str2 === ']') {
    return true
  } else if (str1 === '{' && str2 === '}' ) {
    return true
  } else if (str1 === '(' && str2 === ')' ) {
    return true
  }
  return false
}

function testStr(str) {
  var stack = []
  for (var i = 0; i < str.length; i++) {
    var top = stack[stack.length - 1]
    if (match(top, str[i])) stack.pop()
    else stack.push(str[i])
  }
  if (stack.length === 0) return true
  return false
}

console.log(testStr('([{}])'))
console.log(testStr('{(})'))