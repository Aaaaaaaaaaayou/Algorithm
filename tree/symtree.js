/**
 * Created by ayou on 17/10/15.
 */
function TreeNode (key, label) {
  this.key = key // 用于区别没一个节点，不能相同
  this.label = label || key // 节点的显示值，可以相同
  this.left = null
  this.right = null
}

function Tree () {
  this.root = null
}

Tree.prototype.treeFromOrdering = function (inorder, preorder) {
  this.root = this._treeFromOrdering(inorder, preorder)
}

Tree.prototype._treeFromOrdering = function (inorder, preorder) {
  if (inorder.length === 0) {
    return null
  }

  var node = new TreeNode(preorder[0].key, preorder[0].label)
  for (var rootIndex = 0; rootIndex < preorder.length; rootIndex++) {
    if (inorder[rootIndex].key === preorder[0].key) {
      break
    }
  }

  var leftInorder = inorder.slice(0, rootIndex)
  var leftPreorder = preorder.slice(1, leftInorder.length + 1)
  node.left = this._treeFromOrdering(leftInorder, leftPreorder)

  var rightInorder = inorder.slice(rootIndex + 1)
  var rightPreorder = preorder.slice(leftInorder.length + 1)
  node.right = this._treeFromOrdering(rightInorder, rightPreorder)

  return node
}

Tree.prototype._preOrderWalk = function (p) {
  if (p === null) return
  console.log(p.key, p.label)
  this._preOrderWalk(p.left)
  this._preOrderWalk(p.right)
}

Tree.prototype.preOrderWalk = function () {
  this._preOrderWalk(this.root)
}

Tree.prototype.isSymmetrical = function () {
  if (this.root === null) {
    return true
  }
  return this.isMirror(this.root.left, this.root.right)
}

Tree.prototype.isMirror = function (node1, node2) {
  if (node1 === null && node2 === null) {
    return true
  }

  if (node1 === null || node2 === null) {
    return false
  }

  return node1.label === node2.label ?
    this.isMirror(node1.left, node2.right) && this.isMirror(node1.right, node2.left) :
    false
}

Tree.prototype.toMirror = function () {
  this._toMirror(this.root)
}

Tree.prototype._toMirror = function (node) {
  if (node === null) return
  var tmp = node.left
  node.left = node.right
  node.right = tmp
  this._toMirror(node.left)
  this._toMirror(node.right)
}

var preorder = [
  {key: 1, label: 8},
  {key: 2, label: 6},
  {key: 3, label: 5},
  {key: 4, label: 6},
  {key: 5, label: 7},
  {key: 6, label: 5}
]
var inorder = [
  {key: 3, label: 5},
  {key: 2, label: 6},
  {key: 1, label: 8},
  {key: 5, label: 7},
  {key: 4, label: 6},
  {key: 6, label: 5}
]
var tree = new Tree()
tree.treeFromOrdering(inorder, preorder)
console.log(tree.isSymmetrical())

preorder = [
  {key: 1, label: 8},
  {key: 2, label: 6},
  {key: 3, label: 5},
  {key: 4, label: 7},
  {key: 5, label: 6},
  {key: 6, label: 7},
  {key: 7, label: 5}
]
inorder = [
  {key: 3, label: 5},
  {key: 2, label: 6},
  {key: 4, label: 7},
  {key: 1, label: 8},
  {key: 6, label: 7},
  {key: 5, label: 6},
  {key: 7, label: 5}
]
var tree = new Tree()
tree.treeFromOrdering(inorder, preorder)
console.log(tree.isSymmetrical())

preorder = [
  {key: 5},
  {key: 4},
  {key: 3},
  {key: 6}
]
inorder = [
  {key: 3},
  {key: 4},
  {key: 5},
  {key: 6}
]
var tree = new Tree()
tree.treeFromOrdering(inorder, preorder)
tree.toMirror()
tree.preOrderWalk()