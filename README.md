<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CinemaTS - 项目汇报</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@300;400;500;700&display=swap" rel="stylesheet">
    <!-- Chosen Palette: Warm Neutrals -->
    <!-- Application Structure Plan: 该单页应用被设计成一个交互式的网页幻灯片（Web Slideshow）。用户通过底部的“上一页”和“下一页”按钮在不同的内容“幻灯片”之间导航。每个幻灯片对应源报告中的一页，占据了屏幕的主要视区，创造出专注的演示体验。底部还设有一个进度指示器，让用户清晰地了解当前在演示中的位置。这种结构的选择是为了将静态的Markdown文稿转化为一个动态、连贯且易于控制的演示流程，非常适合项目汇报的场景，能有效引导观众的注意力。 -->
    <!-- Visualization & Content Choices: 1. 报告信息：MVC架构图 -> 目标：组织与解释 -> 可视化方法：使用HTML和Tailwind CSS构建的自定义流程图 -> 交互：静态可视化，通过动画效果引导视觉流 -> 理由：对于架构图，一个清晰的、带有视觉引导的静态图表比复杂的交互图更能快速传达信息。 2. 报告信息：核心功能列表 -> 目标：告知与组织 -> 可视化方法：网格布局中的卡片 -> 交互：悬停时的细微动画效果 -> 理由：卡片式设计将各项功能点模块化，使其易于消化，而悬停效果则增加了视觉的趣味性。 3. 报告信息：幻灯片导航 -> 目标：交互与组织 -> 可视化方法：前后导航按钮和进度点 -> 交互：点击按钮切换幻灯片，进度点同步高亮 -> 理由：这是最符合用户对“演示”心智模型的交互方式，直观且高效。 -->
    <!-- CONFIRMATION: NO SVG graphics used. NO Mermaid JS used. -->
    <style>
        body {
            font-family: 'Noto Sans SC', sans-serif;
            background-color: #FDFBF8;
            color: #4A4A4A;
            overflow: hidden;
        }
        #presentation-container {
            display: flex;
            transition: transform 0.5s ease-in-out;
            width: 100%;
            height: 100%;
        }
        .slide {
            min-width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 2rem;
            opacity: 0;
            transition: opacity 0.6s ease-in-out;
            position: absolute;
            width: 100%;
        }
        .slide.active {
            opacity: 1;
            z-index: 1;
        }
        .nav-button {
            background-color: #D57A66;
            color: white;
            transition: all 0.3s ease;
        }
        .nav-button:hover {
            background-color: #C16A55;
            transform: scale(1.05);
        }
        .nav-button:disabled {
            background-color: #E0A99D;
            cursor: not-allowed;
            opacity: 0.6;
        }
        .progress-dot {
            width: 12px;
            height: 12px;
            border-radius: 50%;
            background-color: #E0A99D;
            transition: all 0.3s ease;
        }
        .progress-dot.active {
            background-color: #D57A66;
            transform: scale(1.2);
        }
        .slide-content {
            max-width: 900px;
            width: 100%;
        }
    </style>
</head>
<body class="h-screen w-screen flex flex-col">

    <main id="presentation-container" class="flex-grow relative">
        <!-- Slide 1: 封面页 -->
        <section class="slide active">
            <div class="slide-content text-center">
                <h1 class="text-5xl md:text-6xl font-bold text-gray-800">CinemaTS - neko影城售票系统</h1>
                <h2 class="text-3xl text-gray-600 mt-4">项目汇报</h2>
                <p class="text-xl text-gray-500 mt-16">汇报人：</p>
            </div>
        </section>

        <!-- Slide 2: 目录 -->
        <section class="slide">
            <div class="slide-content">
                <h2 class="text-4xl font-bold text-center mb-12">汇报目录</h2>
                <ol class="text-2xl space-y-6 list-decimal list-inside text-gray-700">
                    <li><strong>项目简介</strong><p class="text-lg text-gray-500 ml-6">项目是什么？目标是什么？</p></li>
                    <li><strong>核心功能展示</strong><p class="text-lg text-gray-500 ml-6">用户端与管理端的功能亮点</p></li>
                    <li><strong>技术架构解析</strong><p class="text-lg text-gray-500 ml-6">系统是如何构建的？</p></li>
                    <li><strong>总结与展望</strong><p class="text-lg text-gray-500 ml-6">项目成果与未来方向</p></li>
                </ol>
            </div>
        </section>

        <!-- Slide 3: 项目简介 -->
        <section class="slide">
            <div class="slide-content">
                <h2 class="text-4xl font-bold mb-8">1. 项目简介</h2>
                <p class="text-2xl mb-6"><strong>CinemaTS</strong> 是一个基于 **Java 控制台**精心构建的模拟影城票务管理系统。</p>
                <ul class="text-xl space-y-4 list-disc list-inside">
                    <li><strong>核心目标</strong>: 通过一个完整且真实的业务场景，全面实践和展示 **Java后端应用程序开发** 的核心技能。</li>
                    <li><strong>设计思想</strong>: 严格遵循成熟的 **MVC（Model-View-Controller）** 分层设计思想，实现了业务逻辑与用户界面的清晰分离。</li>
                    <li><strong>独特体验</strong>: 在控制台应用中创新性地加入了 **背景音乐 (`BGMPlayer`)** 等细节，致力于提升用户交互的沉浸感。</li>
                </ul>
            </div>
        </section>

        <!-- Slide 4: 核心功能展示 - 概述 -->
        <section class="slide">
            <div class="slide-content">
                <h2 class="text-4xl font-bold text-center mb-8">2. 核心功能展示</h2>
                <p class="text-xl text-center text-gray-600 mb-12">系统功能围绕两大核心角色进行设计，提供了从前台购票到后台管理的全流程解决方案。</p>
                <div class="flex justify-around items-center gap-8">
                    <div class="text-center p-8 border border-gray-200 rounded-xl shadow-lg bg-white w-1/2">
                        <h3 class="text-5xl">👤</h3>
                        <p class="text-2xl font-bold mt-4">普通用户</p>
                        <p class="text-lg text-gray-500 mt-2">便捷的电影票预订服务</p>
                    </div>
                    <div class="text-center p-8 border border-gray-200 rounded-xl shadow-lg bg-white w-1/2">
                        <h3 class="text-5xl">🛠️</h3>
                        <p class="text-2xl font-bold mt-4">影城管理员</p>
                        <p class="text-lg text-gray-500 mt-2">完整的后台运营管理功能</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Slide 5: 核心功能 - 用户端 -->
        <section class="slide">
             <div class="slide-content">
                <h3 class="text-3xl font-bold mb-8">用户端功能亮点</h3>
                <div class="grid md:grid-cols-2 gap-6 text-lg">
                    <div class="p-6 bg-white rounded-lg border border-gray-200">
                        <h4 class="font-bold text-xl mb-2">安全认证</h4>
                        <p>提供安全的注册与登录流程，并在连续登录失败3次后自动触发验证码机制。</p>
                    </div>
                    <div class="p-6 bg-white rounded-lg border border-gray-200">
                        <h4 class="font-bold text-xl mb-2">智能购票</h4>
                        <p>可视化座位图，支持多选，票价会根据VIP等级和购票数量自动计算折扣。</p>
                    </div>
                    <div class="p-6 bg-white rounded-lg border border-gray-200">
                        <h4 class="font-bold text-xl mb-2">便捷管理</h4>
                        <p>随时查看订单历史，并可一键取消未观看的订单。个人中心支持信息修改与充值。</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Slide 6: 核心功能 - 管理端 -->
        <section class="slide">
            <div class="slide-content">
                <h3 class="text-3xl font-bold mb-8">管理端功能亮点</h3>
                 <div class="grid md:grid-cols-2 gap-6 text-lg">
                    <div class="p-6 bg-white rounded-lg border border-gray-200">
                        <h4 class="font-bold text-xl mb-2">权限控制 (RBAC)</h4>
                        <p>管理员通过专属入口登录，系统根据不同权限等级，动态生成可见的操作菜单。</p>
                    </div>
                    <div class="p-6 bg-white rounded-lg border border-gray-200">
                        <h4 class="font-bold text-xl mb-2">全面后台管理 (CRUD)</h4>
                        <p>涵盖用户、电影、VIP体系的全方位增删改查功能。</p>
                    </div>
                     <div class="p-6 bg-white rounded-lg border border-gray-200 col-span-2">
                        <h4 class="font-bold text-xl mb-2">安全与审计</h4>
                        <p>高级管理员可修改删除任意订单，且所有关键后台操作均被记录，确保系统安全、可追溯。</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Slide 7: 技术架构 - 概述 -->
        <section class="slide">
            <div class="slide-content">
                <h2 class="text-4xl font-bold mb-8">3. 技术架构解析</h2>
                <p class="text-xl mb-6">项目采用业界主流的技术栈与经典的 **MVC分层架构**，确保了系统的稳定性、可维护性与未来扩展性。</p>
                <div class="flex justify-center gap-4 text-lg mb-8">
                    <span class="bg-gray-200 text-gray-700 px-4 py-1 rounded-full">Java 1.8</span>
                    <span class="bg-gray-200 text-gray-700 px-4 py-1 rounded-full">Maven</span>
                    <span class="bg-gray-200 text-gray-700 px-4 py-1 rounded-full">MyBatis + MySQL</span>
                    <span class="bg-gray-200 text-gray-700 px-4 py-1 rounded-full">SLF4J + Logback</span>
                </div>
                <blockquote class="border-l-4 border-[#D57A66] pl-4 text-lg italic text-gray-700">
                  **选择MVC架构的理由**：通过明确划分各层职责，实现了业务逻辑与界面展示的高度解耦，代码模块化程度高，易于团队协作与后期维护。
                </blockquote>
            </div>
        </section>

        <!-- Slide 8: 技术架构 - 可视化 -->
        <section class="slide">
            <div class="slide-content">
                <h3 class="text-3xl font-bold text-center mb-10">MVC 架构流程</h3>
                 <div class="text-center text-xl font-mono leading-loose">
                    <div class="bg-orange-100 text-orange-800 p-3 rounded-lg inline-block shadow">View (视图层)</div>
                    <p class="text-3xl text-gray-400 font-sans">↓ ↑</p>
                    <div class="bg-blue-100 text-blue-800 p-3 rounded-lg inline-block shadow">Controller (控制层)</div>
                    <p class="text-3xl text-gray-400 font-sans">↓ ↑</p>
                    <div class="bg-green-100 text-green-800 p-4 rounded-lg shadow">
                        Model (模型层)<br>
                        [ Service → Mapper → Database ]
                    </div>
                </div>
            </div>
        </section>

        <!-- Slide 9: 总结与展望 -->
        <section class="slide">
            <div class="slide-content grid md:grid-cols-2 gap-12">
                <div>
                    <h3 class="text-3xl font-bold mb-6">项目成果总结</h3>
                    <ul class="text-xl space-y-4 list-disc list-inside">
                        <li><strong>功能完整</strong>: 成功实现了用户端与管理端的全部核心功能，业务流程闭环。</li>
                        <li><strong>架构清晰</strong>: 严格遵循MVC分层架构，代码结构清晰，可读性强。</li>
                        <li><strong>运行稳定</strong>: 具备良好的输入验证和异常处理，系统健壮可靠。</li>
                    </ul>
                </div>
                <div>
                    <h3 class="text-3xl font-bold mb-6">未来展望</h3>
                    <ul class="text-xl space-y-4 list-disc list-inside">
                        <li><strong>GUI升级</strong>: 将当前控制台界面升级为图形用户界面 (如JavaFX或Swing)。</li>
                        <li><strong>功能扩展</strong>: 增加在线支付接口、电影推荐算法、用户评论等功能。</li>
                        <li><strong>技术迁移</strong>: 尝试将项目重构为Web应用 (如Spring Boot + Vue)。</li>
                    </ul>
                </div>
            </div>
        </section>

        <!-- Slide 10: 结束页 -->
        <section class="slide">
            <div class="slide-content text-center">
                <h2 class="text-6xl font-bold text-gray-800">感谢聆听</h2>
                <h3 class="text-4xl text-gray-600 mt-6">Q & A</h3>
            </div>
        </section>
    </main>

    <footer class="w-full p-4 bg-[#FDFBF8]/90 backdrop-blur-sm z-10">
        <div class="max-w-4xl mx-auto flex justify-between items-center">
            <button id="prev-button" class="nav-button px-6 py-2 rounded-full font-bold shadow-md">上一页</button>
            <div id="progress-indicator" class="flex space-x-3"></div>
            <button id="next-button" class="nav-button px-6 py-2 rounded-full font-bold shadow-md">下一页</button>
        </div>
    </footer>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const slides = document.querySelectorAll('.slide');
            const prevButton = document.getElementById('prev-button');
            const nextButton = document.getElementById('next-button');
            const progressIndicator = document.getElementById('progress-indicator');
            let currentSlide = 0;

            function createProgressDots() {
                slides.forEach((_, index) => {
                    const dot = document.createElement('div');
                    dot.classList.add('progress-dot');
                    dot.addEventListener('click', () => showSlide(index));
                    progressIndicator.appendChild(dot);
                });
            }

            function updateUI() {
                slides.forEach((slide, index) => {
                    slide.classList.toggle('active', index === currentSlide);
                });

                const dots = progressIndicator.children;
                for (let i = 0; i < dots.length; i++) {
                    dots[i].classList.toggle('active', i === currentSlide);
                }

                prevButton.disabled = currentSlide === 0;
                nextButton.disabled = currentSlide === slides.length - 1;
            }

            function showSlide(index) {
                currentSlide = index;
                updateUI();
            }
            
            prevButton.addEventListener('click', () => {
                if (currentSlide > 0) {
                    showSlide(currentSlide - 1);
                }
            });

            nextButton.addEventListener('click', () => {
                if (currentSlide < slides.length - 1) {
                    showSlide(currentSlide + 1);
                }
            });
            
            document.addEventListener('keydown', (e) => {
                if (e.key === 'ArrowLeft') {
                    prevButton.click();
                } else if (e.key === 'ArrowRight') {
                    nextButton.click();
                }
            });

            createProgressDots();
            showSlide(0);
        });
    </script>
</body>
</html>
