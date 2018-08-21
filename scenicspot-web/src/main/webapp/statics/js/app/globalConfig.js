//全局配置
<script th:inline="javascript">
    ;var globalConfig = {
        pageSize: [[${SystemConfure.pageSize}]],
        systemName:[[${SystemConfure.systemName}]],
        domain:[[${SystemConfure.domain}]],
        SUCCESSCODE:1,
        FAILCODE:0
    }
</script>