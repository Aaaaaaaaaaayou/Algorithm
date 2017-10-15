/**
 * Created by ayou on 17/9/24.
 */

function TreeNode (key) {
  this.key = key
  this.left = null
  this.right = null
}

function Tree () {
  this.root = null
}

Tree.prototype.insert = function (key) {
  var treeNode = new TreeNode(key)
  var node = this.root
  var p
  while(node !== null) {
    p = node
    if (node.key <= key) {
      node = node.right
    } else {
      node = node.left
    }
  }
  if (p == null) {
    this.root = treeNode
  } else if (key < p.key) {
    p.left = treeNode
  } else {
    p.right = treeNode
  }
}

Tree.prototype._preOrderWalk = function (p) {
  if (p === null) return
  console.log(p.key)
  this._preOrderWalk(p.left)
  this._preOrderWalk(p.right)
}

Tree.prototype.preOrderWalk = function () {
  this._preOrderWalk(this.root)
}

Tree.prototype._treeMin = function (node) {
  if (node.left === null) return node.key
  return this._treeMin(node.left)
}

Tree.prototype.treeMin = function () {
  return this._treeMin(this.root)
}

Tree.prototype._treeMax = function (node) {
  if (node.right === null) return node.key
  return this._treeMax(node.right)
}

Tree.prototype.treeMax = function () {
  return this._treeMax(this.root)
}

Tree.prototype.treeSuccessor = function () {
  
}

var arr = [6, 5, 7, 2, 8]

var tree = new Tree()
for (var i = 0; i < arr.length; i++) {
  tree.insert(arr[i])
}

tree.preOrderWalk()
console.log(tree.treeMin())
console.log(tree.treeMax())

