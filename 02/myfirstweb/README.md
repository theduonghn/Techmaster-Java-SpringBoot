Hãy định nghĩa một class `Job` gồm các trường:

`id`, kiểu String, mã tin tuyển dụng cần phải là duy nhất (unique)
`title`: kiểu String, tiêu đề tuyển dụng
`description`: kiểu String, mô tả tin tuyển dụng
`location`: [Hanoi, Hai Phong, Da Nang, Ho Chi Minh]
`min_salary`: int, lương tối thiểu
`max_salary`: int, lương tối đa
`email_to`: String, email nhà tuyển dụng
Tạo REST API để thêm sửa xoá job, đồng thời bổ xung 4 phương thức GET
![image](https://user-images.githubusercontent.com/95962543/168468877-5374440e-a790-4b84-b31c-daf91ad2d552.png)
![image](https://user-images.githubusercontent.com/95962543/168469885-54a9c881-f930-4696-a028-7a1f687fc82c.png)
![image](https://user-images.githubusercontent.com/95962543/168469962-72efa467-d171-4048-9c67-5e5227cbf524.png)
![image](https://user-images.githubusercontent.com/95962543/168470100-155da328-e17b-472a-8cb3-2bee609f2fd4.png)

http://localhost:8080/job/sortbylocation: sắp xếp danh sách job theo thành phố tuyển
![image](https://user-images.githubusercontent.com/95962543/168469721-6f994c0d-afb5-4e60-830a-11da693fa63e.png)

http://localhost:8080/job/salary/{salary}: tìm các job mà {salary} trong khoảng min_salary và max_salary
![image](https://user-images.githubusercontent.com/95962543/168469026-ae25b567-e394-4c23-994d-3a0fad10367a.png)

http://localhost:8080/job/keyword/{keyword} tìm các job mà title hoặc description chứa {keyword}
![image](https://user-images.githubusercontent.com/95962543/168469106-07dde6dd-9eb3-448d-9bd3-a9d1d3de3ff4.png)

http://localhost:8080/job/query?location={location}&keyword={keyword} tìm các job mà title hoặc description chứa {keyword} đồng thời location ={location}
![image](https://user-images.githubusercontent.com/95962543/168469534-ff746909-6f48-432d-bba2-7a4f23e5a32e.png)
