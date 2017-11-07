Ext.namespace('Ext.ui.plugins');

Ext.ui.plugins.ComboPageSize = function(config) {
    Ext.apply(this, config);
};

Ext.extend(Ext.ui.plugins.ComboPageSize, Ext.util.Observable, {

    pageSizes: [5, 10, 15, 20, 25, 30, 50, 100, 200],
    prefixText: '每页 ',
    postfixText: '条',
    addToItem: true,    //true添加到items中去，配合index；false则直接添加到最后
    index: 10,           //在items中的位置
    init: function(pagingToolbar) {
        var ps = this.pageSizes;
        var combo = new Ext.form.ComboBox({
            typeAhead: true,
            triggerAction: 'all',
            lazyRender: true,
            mode: 'local',
            width: 45,
            store: ps,
            enableKeyEvents: true,
            editable: true,
            loadPages: function() {
                var rowIndex = 0;
                var gp = pagingToolbar.findParentBy(
                                function(ct, cmp) { return (ct instanceof Ext.grid.GridPanel) ? true : false; }
                              );
                var sm = gp.getSelectionModel();
                if (undefined != sm && sm.hasSelection()) {
                    if (sm instanceof Ext.grid.RowSelectionModel) {
                        rowIndex = gp.store.indexOf(sm.getSelected());
                    } else if (sm instanceof Ext.grid.CellSelectionModel) {
                        rowIndex = sm.getSelectedCell()[0];
                    }
                }
                rowIndex += pagingToolbar.cursor;
                pagingToolbar.doLoad(Math.floor(rowIndex / pagingToolbar.pageSize) * pagingToolbar.pageSize);
            },
            listeners: {
                select: function(c, r, i) {
                    pagingToolbar.pageSize = ps[i];
                    this.loadPages();
                },
                blur: function() {
                    var pagesizeTemp = Number(this.getValue());
                    if (isNaN(pagesizeTemp)) {
                        this.setValue(pagingToolbar.pageSize);
                        return;
                    }
                    pagingToolbar.pageSize = Number(this.getValue());
                    this.loadPages();
                }
            }
        });
        if (this.addToItem) {
            var inputIndex = this.index;
            if (inputIndex > pagingToolbar.items.length) inputIndex = pagingToolbar.items.length;
            pagingToolbar.insert(++inputIndex, '-');
            pagingToolbar.insert(++inputIndex, this.prefixText);
            pagingToolbar.insert(++inputIndex, combo);
            pagingToolbar.insert(++inputIndex, this.postfixText);
        }
        else {
            pagingToolbar.add('-');
            pagingToolbar.add(this.prefixText);
            pagingToolbar.add(combo);
            pagingToolbar.add(this.postfixText);
        }
        pagingToolbar.on({
            beforedestroy: function() {
                combo.destroy();
            },
            change: function() {
                combo.setValue(pagingToolbar.pageSize);

            }
        });

    }
})