import request from '@/utils/request'

// 查询班级列表
export function listClassInfo(query) {
  return request({
    url: '/business/class/list',
    method: 'get',
    params: query
  })
}

// 查询所有班级（下拉选项用）
export function allClassInfo() {
  return request({
    url: '/business/class/all',
    method: 'get'
  })
}

// 查询班级详细
export function getClassInfo(id) {
  return request({
    url: '/business/class/' + id,
    method: 'get'
  })
}

// 新增班级
export function addClassInfo(data) {
  return request({
    url: '/business/class',
    method: 'post',
    data: data
  })
}

// 修改班级
export function updateClassInfo(data) {
  return request({
    url: '/business/class',
    method: 'put',
    data: data
  })
}

// 删除班级
export function delClassInfo(id) {
  return request({
    url: '/business/class/' + id,
    method: 'delete'
  })
}
