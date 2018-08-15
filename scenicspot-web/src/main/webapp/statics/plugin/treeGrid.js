/**
 * 需要在表格行添加俩个属性,data-pid上级ID;data-id自己的ID,
 * <tr data-pid="0" data-id="150">
 * 需要在table上添加class:tree-grid
 */


var TreeGrid = {};
TreeGrid.treeArray = [];
TreeGrid.trArray = [];
TreeGrid.treeObject = {};
TreeGrid.applyTree = function() {
	// 第一步 :采集所有行
	$(".tree-grid").find("tr").each(function(k) {
		var trObj = {};
		trObj.tr = $(this);
		trObj.pid = $(this).attr("data-pid");
		trObj.id = $(this).attr("data-id");
		trObj.isOpen = true;
		TreeGrid.trArray.push(trObj);
	});
	// 第二步 :格式化为树结构
	for (var j = 0; j < TreeGrid.trArray.length; j++) {
		var trObj = TreeGrid.trArray[j];
		TreeGrid.treeObject["treeItem" + trObj.id] = trObj;
		if (trObj.pid == 0 || trObj.pid == null) {// 根节点
			TreeGrid.treeArray.push(trObj);
		} else {
			for (var i = 0; i < TreeGrid.trArray.length; i++) {
				var item = TreeGrid.trArray[i];
				if (item.id == trObj.pid) {
					if (item.subRows == null) {
						item.subRows = [];
					}
					item.subRows.push(trObj);
				}
			}
		}
	}
	var tree = $(".tree-grid");
	$(tree).empty();// 先将表格滞空
	TreeGrid.formateTr(TreeGrid.treeArray, tree, 0);// 渲染树结构
	// 给折叠按钮添加事件侦听
	$(".tree-open-btn").click(TreeGrid.onOpenBtnClick);
	$(".tree-close-btn").click(TreeGrid.onCloseBtnClick);
}

TreeGrid.onOpenBtnClick = function(event) {
	var value = $(event.currentTarget).attr("data-id");
	var trObj = TreeGrid.treeObject["treeItem" + value];
	trObj.isOpen = true;
	TreeGrid.toggleBtn(trObj);
	TreeGrid.showTr(trObj.subRows);
}

TreeGrid.onCloseBtnClick = function(event) {
	var value = $(event.currentTarget).attr("data-id");
	var trObj = TreeGrid.treeObject["treeItem" + value];
	trObj.isOpen = false;
	TreeGrid.toggleBtn(trObj);
	TreeGrid.hideTr(trObj.subRows);
}
// 显示或隐藏树的展开关闭按钮
TreeGrid.toggleBtn = function(trObj) {
	var firstTd = $(trObj.tr).find("td:first");
	var btns = $(firstTd).find("button");
	$(firstTd).find("button").each(function() {
		var action = $(this).attr("data-action");
		if (trObj.isOpen) {// 隐藏
			if ("collapse" == action) {
				$(this).show();
			} else {
				$(this).hide();
			}
		} else {
			if ("collapse" == action) {
				$(this).hide();
			} else {
				$(this).show();
			}
		}
	});
}

// 隐藏时隐藏所有子集
TreeGrid.hideTr = function(array) {
	for (var i = 0; i < array.length; i++) {
		var item = array[i];
		$(item.tr).hide();
		if (item.subRows != null) {
			TreeGrid.hideTr(item.subRows);
		}
	}
}

// 显示时按需显示
TreeGrid.showTr = function(array) {
	for (var i = 0; i < array.length; i++) {
		var item = array[i];
		$(item.tr).show();
		if (item.isOpen) {
			if (item.subRows != null) {
				TreeGrid.showTr(item.subRows, true);
			}
		}
	}
}

TreeGrid.formateTr = function(treeArray, tree, deep) {
	for (var i = 0; i < treeArray.length; i++) {
		var item = treeArray[i];
		$(item.tr).attr("data-deep", deep);
		$(item.tr).addClass("tr-deep-" + deep);
		$(item.tr).addClass("tr-open");
		$(tree).append(item.tr);
		var td = $(item.tr).find("td:first");
		if (item.subRows != null) {
			td
					.prepend('<button class="tree-close-btn" data-pid="'
							+ item.pid
							+ '" data-id="'
							+ item.id
							+ '" data-action="collapse" type="button" style="display: block;">关闭</button>');
			td
					.prepend('<button class="tree-open-btn" data-pid="'
							+ item.pid
							+ '" data-id="'
							+ item.id
							+ '" data-action="expand" type="button" style="display: none;">展开</button>');
			TreeGrid.formateTr(item.subRows, tree, deep + 1);
		} else {
			td
					.prepend('<div style="width: 25px;display: inline-block;"></div>');
		}
	}
}
TreeGrid.applyTree();
