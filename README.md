# EmptyRecyclerAdapter
可配置EmptyView的RecyclerAdapter
----------------------------------------
使用方法：</br>
1. 继承BaseWithEmptyViewAdapter，并重写四个方法
```Java
    @Override
    public RecyclerView.ViewHolder onCreateItemHolder(LayoutInflater mInflater, ViewGroup parent, int viewType) {
        // 此处创建Item的ViewHolder
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateEmptyHolder(LayoutInflater mInflater, ViewGroup parent, int viewType) {
        // 此处创建EmtptyView的ViewHolder
        return null;
    }

    @Override
    public void onBindItemHolder(List<String> mDataList, RecyclerView.ViewHolder holder, int position) {
        // 此处绑定Item的Holder
    }

    @Override
    public void onBindEmptyHolder(RecyclerView.ViewHolder holder) {
        // 此处绑定EmptyView的Holder
    }
```
2. 在具体使用时，主要关注三个方法：
```Java
// 向列表中增加数据
emptyAdapter.addData(dataList);

// 清空列表中的数据并显示EmptyView
emptyAdapter.showEmptyView();

// 获取列表中序列为position的数据实例
emptyAdapter.getItem(position);
```
3. 其他功能，比如添加onItemClickListener，和源生的实现方法一致。
